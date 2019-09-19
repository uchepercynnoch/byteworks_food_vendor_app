package com.byteworks.devops.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private long customerId;
    @Column(name = "customer_username")
    private String customerUsername;
    @Column(name = "customer_password")
    private String customerPassword;
    @Column(name = "customer_confirm_password")
    private String customerConfirmPassword;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "customer_location")
    private String customerLocation;
    @Column(name = "customer_phone_number")
    private String customerPhoneNumber;
    @Column(name = "created_at")
    private Date created_at;
    @Column(name = "updated_at")
    private Date updated_at;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(joinColumns = {@JoinColumn(name = "customer_id")},
            inverseJoinColumns = {@JoinColumn(name = "id")})
    private List<Permission> permissions = new ArrayList<>();

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomerConfirmPassword() {
        return customerConfirmPassword;
    }

    public void setCustomerConfirmPassword(String customerConfirmPassword) {
        this.customerConfirmPassword = customerConfirmPassword;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerLocation() {
        return customerLocation;
    }

    public void setCustomerLocation(String customerLocation) {
        this.customerLocation = customerLocation;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @PrePersist
    public void onPrePersist() {
        this.audit();
    }

    @PreUpdate
    public void onPreUpdate() {
        this.audit();
    }

    private void audit() {
        setCreated_at(new Date());
    }
}

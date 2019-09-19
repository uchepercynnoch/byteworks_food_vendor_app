package com.byteworks.devops.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long orderId;
    @Column(name = "customer_order_id")
    private String customerOrderId;
    @Column(name = "customer_order_date")
    private Date customerOrderDate;
    @Column(name = "customer_order_number")
    private int customerOrderNumber;
    @Column(name = "amount_paid")
    private double amountPaid;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "customer_location")
    private String customerLocation;
    @Column(name = "customer_phone_number")
    private String customerPhoneNumber;

    public CustomerOrder() {
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(String customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public Date getCustomerOrderDate() {
        return customerOrderDate;
    }

    public void setCustomerOrderDate(Date customerOrderDate) {
        this.customerOrderDate = customerOrderDate;
    }

    public int getCustomerOrderNumber() {
        return customerOrderNumber;
    }

    public void setCustomerOrderNumber(int customerOrderNumber) {
        this.customerOrderNumber = customerOrderNumber;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
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

    @PrePersist
    public void onPrePersist() {
        this.audit();
    }

    @PreUpdate
    public void onPreUpdate() {
        this.audit();
    }

    private void audit() {
        setCustomerOrderDate(new Date());
    }
}

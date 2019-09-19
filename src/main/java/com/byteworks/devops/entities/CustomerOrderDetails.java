package com.byteworks.devops.entities;

import javax.persistence.*;

@Entity
@Table(name = "customer_order_details")
public class CustomerOrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "customer_order_details_id")
    private String customerOrderDetailsId;
    @Column(name = "quantity_of_food")
    private int quantityOfFood;
    @Column(name = "price_of_food")
    private double priceOfFood;
    @Column(name = "amount_paid")
    private double amountPaid;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_order_id", nullable = false,
            foreignKey = @ForeignKey(name = "customer_order_details_order_fk"))
    private CustomerOrder customerOrder;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_food_id", nullable = false,
            foreignKey = @ForeignKey(name = "customer_order_details_food_fk"))
    private FoodInformation food;

    public CustomerOrderDetails() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerOrderDetailsId() {
        return customerOrderDetailsId;
    }

    public void setCustomerOrderDetailsId(String customerOrderDetailsId) {
        this.customerOrderDetailsId = customerOrderDetailsId;
    }

    public int getQuanityOfFood() {
        return quantityOfFood;
    }

    public void setQuanityOfFood(int quantityOfFood) {
        this.quantityOfFood = quantityOfFood;
    }

    public double getPriceOfFood() {
        return priceOfFood;
    }

    public void setPriceOfFood(double priceOfFood) {
        this.priceOfFood = priceOfFood;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public FoodInformation getFood() {
        return food;
    }

    public void setFood(FoodInformation food) {
        this.food = food;
    }
}

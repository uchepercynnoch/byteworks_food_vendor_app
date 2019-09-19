package com.byteworks.devops.models;

import com.byteworks.devops.entities.FoodInformation;

public class CartInformation {
    private FoodInformation foodInformation;
    private int quantity;

    public CartInformation() {
        this.quantity = 0;
    }

    public FoodInformation getFoodInformation() {
        return foodInformation;
    }

    public void setFoodInformation(FoodInformation foodInformation) {
        this.foodInformation = foodInformation;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return this.foodInformation.getFoodPrice() * this.quantity;
    }
}

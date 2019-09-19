package com.byteworks.devops.models;


import com.byteworks.devops.entities.Customer;
import com.byteworks.devops.entities.FoodInformation;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int orderNumber;
    private Customer customer;
    private final List<CartInformation> cartInformationList = new ArrayList<>();

    public Cart() {

    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<CartInformation> getCartInformation() {
        return this.cartInformationList;
    }

    private CartInformation findFoodInformationByCode(String code) {
        for (CartInformation cartInformation : this.cartInformationList) {
            if (cartInformation.getFoodInformation().getFoodCode().equals(code)) {
                return cartInformation;
            }
        }
        return null;
    }

    public void addProduct(FoodInformation foodInformation, int quantity) {
        CartInformation cartInformation = this.findFoodInformationByCode(foodInformation.getFoodCode());

        if (cartInformation == null) {
            cartInformation = new CartInformation();
            cartInformation.setQuantity(0);
            cartInformation.setFoodInformation(foodInformation);
            this.cartInformationList.add(cartInformation);
        }
        int newQuantity = cartInformation.getQuantity() + quantity;
        if (newQuantity <= 0) {
            this.cartInformationList.remove(cartInformation);
        } else {
            cartInformation.setQuantity(newQuantity);
        }
    }

    private void updateProduct(String code, int quantity) {
        CartInformation cartInformation = this.findFoodInformationByCode(code);

        if (cartInformation != null) {
            if (quantity <= 0) {
                this.cartInformationList.remove(cartInformation);
            } else {
                cartInformation.setQuantity(quantity);
            }
        }
    }

    public void removeProduct(FoodInformation foodInformation) {
        CartInformation cartInformation = this.findFoodInformationByCode(foodInformation.getFoodCode());
        if (cartInformation != null) {
            this.cartInformationList.remove(cartInformation);
        }
    }

    public boolean isEmpty() {
        return this.cartInformationList.isEmpty();
    }

    public int getQuantityTotal() {
        int quantity = 0;
        for (CartInformation line : this.cartInformationList) {
            quantity += line.getQuantity();
        }
        return quantity;
    }

    public double getAmountTotal() {
        double total = 0;
        for (CartInformation line : this.cartInformationList) {
            total += line.getAmount();
        }
        return total;
    }

    public void updateQuantity(Cart cart) {
        if (cart != null) {
            List<CartInformation> cartInformationList = cart.getCartInformation();
            for (CartInformation cartInformation : cartInformationList) {
                this.updateProduct(cartInformation.getFoodInformation().getFoodCode(), cartInformation.getQuantity());
            }
        }
    }
}

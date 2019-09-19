package com.byteworks.devops.entities;

import javax.persistence.*;

@Entity
@Table(name = "food_information")
public class FoodInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private long foodId;
    @Column(name = "food_code")
    private String foodCode;
    @Column(name = "food_name")
    private String foodName;
    @Column(name = "food_price")
    private double foodPrice;
    @Column(name = "vendor_location")
    private String vendorLocation;


    public FoodInformation() {
    }

    public long getFoodId() {
        return foodId;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }

    public String getFoodCode() {
        return foodCode;
    }

    public void setFoodCode(String foodCode) {
        this.foodCode = foodCode;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getVendorLocation() {
        return vendorLocation;
    }

    public void setVendorLocation(String vendorLocation) {
        this.vendorLocation = vendorLocation;
    }
}

package com.example.restaurantmanagement.models;

public class Food {
    private String description;
    private String name;
    private double price;
    private int foodCategory; ////Food Category 1:çorba 2:ara yemek 3:ana yemek 4:tatlı 5:içecek

    public Food () {}
    public Food(String name, String description, double price, int foodCategory) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.foodCategory = foodCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(int foodCategory) {
        this.foodCategory = foodCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

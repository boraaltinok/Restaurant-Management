package com.example.restaurantmanagement.models;

import java.util.ArrayList;

public class Order {
    private String tableNumber;
    private Double totalPrice;
    private ArrayList<Food> orderedFoods;

    public Order(){

    }
    public Order(String tableNumber, Double totalPrice, ArrayList<Food> orderedFoods){
        this.tableNumber = tableNumber;
        this.totalPrice = totalPrice;
        this.orderedFoods = orderedFoods;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ArrayList<Food> getOrderedFoods() {
        return orderedFoods;
    }

    public void setOrderedFoods(ArrayList<Food> orderedFoods) {
        this.orderedFoods = orderedFoods;
    }
}

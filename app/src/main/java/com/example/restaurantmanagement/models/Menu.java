package com.example.restaurantmanagement.models;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    List<List<Food>> menu = new ArrayList<List<Food>>();

    public void setMenu(List<List<Food>> menu) {
        this.menu = menu;
    }

    public Menu(){}

    public Menu(List<List<Food>> menu){
        this.menu = menu;
    }

    //Food Category 1:çorba 2:ara yemek 3:ana yemek 4:tatlı 5:içecek
    public void addFoodToMenu(Food food, int foodCategory){
        menu.get(foodCategory).add(food);
    }
}

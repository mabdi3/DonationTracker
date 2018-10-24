package com.example.abdim.donationtracker.models;

public class ItemCategory {
    String categoryName;

    public ItemCategory(String name) {
        categoryName = name;
    }

    public String toString(){
        return categoryName;
    }
}

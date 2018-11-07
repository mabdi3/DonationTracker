package com.example.abdim.donationtracker.models;

public class ItemCategory {
    String categoryName;

    public ItemCategory(String name) {
        categoryName = name;
    }

    public ItemCategory() {
    }

    /**
     * converts the itemcategory to a string by taking the name
     * @return the category's name
     */
    public String toString(){
        return categoryName;
    }
}

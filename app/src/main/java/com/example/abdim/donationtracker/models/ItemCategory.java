package com.example.abdim.donationtracker.models;

import java.io.Serializable;

public class ItemCategory implements Serializable {
    String categoryName;

    public ItemCategory(String name) {
        categoryName = name;
    }

    public ItemCategory() {
    }

    public String toString(){
        return categoryName;
    }
}

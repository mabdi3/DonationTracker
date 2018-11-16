package com.example.abdim.donationtracker.models;

/**
 * Represents an ItemCategory
 */
public class ItemCategory {
    String categoryName;

    public ItemCategory() {
    }
    /**
     * Constructs ItemCategory object.
     * @param name the name of the ItemCategory
     */
    public ItemCategory(String name) {
        categoryName = name;
    }

    /**
     * converts the item category to a string by taking the name
     * @return the category's name
     */
    public String toString(){
        return categoryName;
    }
}

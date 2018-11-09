package com.example.abdim.donationtracker.models;

/**
 * Represents an ItemCategory
 */
public class ItemCategory {
    String categoryName;

    /**
     * Constructs ItemCategory object.
     * @param name the name of the ItemCategory
     */
    public ItemCategory(String name) {
        categoryName = name;
    }

    /**
     * Constructs ItemCategory object, leaving categoryNam as null.
     */
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

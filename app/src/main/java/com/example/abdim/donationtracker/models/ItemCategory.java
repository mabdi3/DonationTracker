package com.example.abdim.donationtracker.models;

/**
 * Represents an ItemCategory
 */
public class ItemCategory {
    private String categoryName;

    /**
     * Default constructor
     */
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
     * Getter for categoryName
     * @return categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * converts the item category to a string by taking the name
     * @return the category's name
     */
    public String toString(){
        return categoryName;
    }
}

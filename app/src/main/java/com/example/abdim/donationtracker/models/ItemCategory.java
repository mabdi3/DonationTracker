package com.example.abdim.donationtracker.models;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemCategory that = (ItemCategory) o;
        return Objects.equals(categoryName, that.categoryName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(categoryName);
    }
}

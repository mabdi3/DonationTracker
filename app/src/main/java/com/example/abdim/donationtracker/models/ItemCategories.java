package com.example.abdim.donationtracker.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents ItemCategories
 */
@SuppressWarnings("UtilityClass")
public class ItemCategories {
    // use this because a lot of views need a list, so instead of always converting from HashSet to ArrayList, just keep an ArrayList handy
    private static List<ItemCategory> itemCategoriesAsList = new ArrayList<ItemCategory>(Arrays.asList(
            new ItemCategory("Clothing"),
            new ItemCategory("Hat"),
            new ItemCategory("Kitchen"),
            new ItemCategory("Electronics"),
            new ItemCategory("Household"),
            new ItemCategory("Other")
    ));

    /**
     * Returns the list of item categories as an ArrayList
     * @return The item categories as an ArrayList
     */
    public static List<ItemCategory> getItemCategoriesAsList() {
        return itemCategoriesAsList;
    }
    /**
     * Sets the ArrayList variation of the item categories
     * @param itemCategoriesAsList a list of item categories
     */
    public static void setItemCategoriesAsList(List<ItemCategory> itemCategoriesAsList) {
        ItemCategories.itemCategoriesAsList = itemCategoriesAsList;
    }
}

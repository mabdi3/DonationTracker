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

    // use this hashset to do O(1) contains
    private static Set<ItemCategory> itemCategoriesAsHashSet = new HashSet<>(Arrays.asList(
            new ItemCategory("Clothing"),
            new ItemCategory("Hat"),
            new ItemCategory("Kitchen"),
            new ItemCategory("Electronics"),
            new ItemCategory("Household"),
            new ItemCategory("Other")
    ));

    // use this because a lot of views need a list, so instead of always converting from
    // HashSet to ArrayList, just keep an ArrayList handy
    private static List<ItemCategory> itemCategoriesAsList = new ArrayList<>(Arrays.asList(
            new ItemCategory("Clothing"),
            new ItemCategory("Hat"),
            new ItemCategory("Kitchen"),
            new ItemCategory("Electronics"),
            new ItemCategory("Household"),
            new ItemCategory("Other")
    ));

    /**
     * Returns the list of itemcategories as an ArrayList
     * @return The item catergories as an ArrayList
     */
    public static List<ItemCategory> getItemCategoriesAsList() {
        return itemCategoriesAsList;
    }

}

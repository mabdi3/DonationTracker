package com.example.abdim.donationtracker.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ItemCategories {

    // use this HashSet to do O(1) contains
    private static Set<ItemCategory> itemCategoriesAsHashSet = new HashSet<ItemCategory>(Arrays.asList(
            new ItemCategory("Clothing"),
            new ItemCategory("Hat"),
            new ItemCategory("Kitchen"),
            new ItemCategory("Electronics"),
            new ItemCategory("Household"),
            new ItemCategory("Other")
    ));

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
     * Attempts to add a new category to the item categories hash set.
     * @param newCategory the category to add to the hash set
     * @return true if an item is added, false otherwise
     */
    public static boolean addItemCategory(ItemCategory newCategory) {
        if (itemCategoriesAsHashSet.contains(newCategory)) {
            return false;
        } else {
            itemCategoriesAsHashSet.add(newCategory);
            itemCategoriesAsList.add(newCategory);
            return true;
        }
    }

    /**
     * Returns the list of item categories as a set
     * @return The item categories as a HashSet
     */
    public static Set<ItemCategory> getItemCategoriesAsHashSet() {
        return itemCategoriesAsHashSet;
    }

    /**
     * Sets a new HashSet as the set of all item categories
     * @param itemCategoriesAsHashSet new HashSet of categories
     */
    public static void setItemCategoriesAsHashSet(Set<ItemCategory> itemCategoriesAsHashSet) {
        ItemCategories.itemCategoriesAsHashSet = itemCategoriesAsHashSet;
    }

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

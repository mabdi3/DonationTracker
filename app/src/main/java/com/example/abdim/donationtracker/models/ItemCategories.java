package com.example.abdim.donationtracker.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ItemCategories {

    // use this hashset to do O(1) contains
    private static Set<ItemCategory> itemCategoriesAsHashSet = new HashSet<ItemCategory>(Arrays.asList(
            new ItemCategory("Clothing"),
            new ItemCategory("Hat"),
            new ItemCategory("Kitchen"),
            new ItemCategory("Electronics"),
            new ItemCategory("Household"),
            new ItemCategory("Other")
    ));

    // use this because a lot of views need a list, so instead of always converting from hashset to arraylist, just keep an arraylist handy
    private static List<ItemCategory> itemCategoriesAsList = new ArrayList<ItemCategory>(Arrays.asList(
            new ItemCategory("Clothing"),
            new ItemCategory("Hat"),
            new ItemCategory("Kitchen"),
            new ItemCategory("Electronics"),
            new ItemCategory("Household"),
            new ItemCategory("Other")
    ));

    /**
     * Attempts to add a new location to the container. Returns true if added a whole new location.
     * False otherwise.
     *
     *
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
     * Returns the list of itemcategories as a set
     * @return The item catergories as a hashset
     */
    public static Set<ItemCategory> getItemCategoriesAsHashSet() {
        return itemCategoriesAsHashSet;
    }

    /**
     * Sets a new hashset as the set of all itemcategories
     * @param itemCategoriesAsHashSet new hashset of categories
     */
    public static void setItemCategoriesAsHashSet(Set<ItemCategory> itemCategoriesAsHashSet) {
        ItemCategories.itemCategoriesAsHashSet = itemCategoriesAsHashSet;
    }

    /**
     * Returns the list of itemcategories as an ArrayList
     * @return The item catergories as an ArrayList
     */
    public static List<ItemCategory> getItemCategoriesAsList() {
        return itemCategoriesAsList;
    }

    /**
     * Sets the arraylist variation of the itemcategories
     * @param itemCategoriesAsList a list of itemcategories
     */
    public static void setItemCategoriesAsList(List<ItemCategory> itemCategoriesAsList) {
        ItemCategories.itemCategoriesAsList = itemCategoriesAsList;
    }
}

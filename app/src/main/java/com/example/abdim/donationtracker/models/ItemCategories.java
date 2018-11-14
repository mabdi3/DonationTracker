package com.example.abdim.donationtracker.models;

import android.util.Log;

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

    /*
    use this because a lot of views need a list, so instead of always converting f
    from hashSet to arrayList, just keep an arrayList handy
    */
    private static List<ItemCategory> itemCategoriesAsList = new ArrayList<>(Arrays.asList(
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
    public static boolean addItemCategory() {
        return addItemCategory();
    }

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

    public static Set<ItemCategory> getItemCategoriesAsHashSet() {
        return itemCategoriesAsHashSet;
    }

    public static void setItemCategoriesAsHashSet(Set<ItemCategory> itemCategoriesAsHashSet) {
        ItemCategories.itemCategoriesAsHashSet = itemCategoriesAsHashSet;
    }

    public static List<ItemCategory> getItemCategoriesAsList() {
        Log.d("hello", "getting here");
        Log.d("hello", itemCategoriesAsList.toString());
        return itemCategoriesAsList;
    }

    public static void setItemCategoriesAsList(List<ItemCategory> itemCategoriesAsList) {
        ItemCategories.itemCategoriesAsList = itemCategoriesAsList;
    }
}

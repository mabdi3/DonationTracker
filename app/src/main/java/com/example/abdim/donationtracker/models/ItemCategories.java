package com.example.abdim.donationtracker.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Represents ItemCategories
 */
//@SuppressWarnings("UtilityClass")
public class ItemCategories {
//    private static final Set<ItemCategory> itemCategoriesAsHashSet = new HashSet<>(Arrays.asList(
//            new ItemCategory("Clothing"),
//            new ItemCategory("Hat"),
//            new ItemCategory("Kitchen"),
//            new ItemCategory("Electronics"),
//            new ItemCategory("Household"),
//            new ItemCategory("Other")
//    ));

    /*
    use this because a lot of views need a list, so instead of always converting f
    from hashSet to arrayList, just keep an arrayList handy
    */
    private static final List<ItemCategory> itemCategoriesAsList = new ArrayList<>(Arrays.asList(
            new ItemCategory("Clothing"),
            new ItemCategory("Hat"),
            new ItemCategory("Kitchen"),
            new ItemCategory("Electronics"),
            new ItemCategory("Household"),
            new ItemCategory("Other")
    ));

    /**
     * Getter for itemCategoriesList
     * @return list of itemCategories
     */
    public static List<ItemCategory> getItemCategoriesAsList() {
        return Collections.unmodifiableList(itemCategoriesAsList);
    }

}

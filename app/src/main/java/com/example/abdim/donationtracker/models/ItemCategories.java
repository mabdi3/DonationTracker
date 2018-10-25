package com.example.abdim.donationtracker.models;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ItemCategories {

    public static Set<ItemCategory> itemCategories = new HashSet<ItemCategory>(Arrays.asList(
            new ItemCategory("Clothing"),
            new ItemCategory("Hat"),
            new ItemCategory("Kitchen"),
            new ItemCategory("Electronics"),
            new ItemCategory("Household"),
            new ItemCategory("Other")
    ));
}

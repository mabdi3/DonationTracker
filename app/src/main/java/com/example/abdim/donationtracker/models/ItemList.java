package com.example.abdim.donationtracker.models;

import java.util.ArrayList;
import java.util.List;

public class ItemList {
    private static List<Item> itemList = new ArrayList<>();

    public static void addItem(Item newItem) {
        itemList.add(newItem);
    }

    public List<Item> getItemList() {
        return itemList;
    }
}

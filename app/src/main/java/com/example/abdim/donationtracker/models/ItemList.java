package com.example.abdim.donationtracker.models;

import java.io.Serializable;
import java.util.ArrayList;

public class ItemList implements Serializable {
    private ArrayList<Item> itemList;

    public ItemList() {
        itemList = new ArrayList<>();
    }

    /**
     * Adds a new Item object to the itemList
     * @param newItem
     */
    public void addItem(Item newItem) {
        itemList.add(newItem);
    }

    /**
     * Returns the list of items
     * @return the held list of items
     */
    public ArrayList<Item> getItemList() {
        return itemList;
    }
}

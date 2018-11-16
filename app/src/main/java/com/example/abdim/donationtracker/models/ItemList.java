package com.example.abdim.donationtracker.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents an ItemList
 */
public class ItemList implements Serializable {
    private final List<Item> itemList;

    /**
     * Creates ItemList object and assigns to empty ArrayList.
     */
    public ItemList() {
        itemList = new ArrayList<>();
    }

    /**
     * Adds a new Item object to the itemList
     * @param newItem newItem
     */
    public void addItem(Item newItem) {
        itemList.add(newItem);
    }

}

package com.example.abdim.donationtracker.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ItemList implements Serializable {
    private ArrayList<Item> itemList;

    public ItemList() {
        itemList = new ArrayList<>();
    }

    public void addItem(Item newItem) {
        itemList.add(newItem);
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }
}

package com.example.abdim.donationtracker.models;

public class Donation {
    Location location;
    String time;
    ItemList items;
    String name;

    Donation(Location l, String t, String n, ItemList i) {
        location = l;
        time = t;
        name = n;
        items = new ItemList();
    }

    public ItemList getItems() {
        return items;
    }

    public void addDonationItem(Item i) {
        items.addItem(i);
    }
}

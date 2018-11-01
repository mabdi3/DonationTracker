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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setItems(ItemList items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemList getItems() {
        return items;
    }

    public void addDonationItem(Item i) {
        items.addItem(i);
    }
}

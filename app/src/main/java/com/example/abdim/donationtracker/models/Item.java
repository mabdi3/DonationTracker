package com.example.abdim.donationtracker.models;

import android.media.Image;

import java.util.Locale;

public class Item {
    String name;
    String description;
    int quantity;
    Image itemPhoto;
    Location location;
    ItemCategory category;
    String time;

    Item (String n, String d, int q, Location l, ItemCategory c, String t) {
        name = n;
        description = d;
        quantity = q;
        itemPhoto = null;
        location = l;
        category = c;
        time = t;
    }
    Item (String n, String d, int q, Image i, Location l, ItemCategory c, String t) {
        name = n;
        description = d;
        quantity = q;
        itemPhoto = i;
        location = l;
        category = c;
        time = t;
    }

    public Image getItemPhoto() {
        return itemPhoto;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }
}

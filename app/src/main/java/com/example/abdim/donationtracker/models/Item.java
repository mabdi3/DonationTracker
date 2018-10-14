package com.example.abdim.donationtracker.models;

import android.media.Image;

public class Item {
    String name;
    String description;
    int quantity;
    Image itemPhoto;

    Item (String n, String d, int q) {
        name = n;
        description = d;
        quantity = q;
        itemPhoto = null;
    }
    Item (String n, String d, int q, Image i) {
        name = n;
        description = d;
        quantity = q;
        itemPhoto = i;
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
}

package com.example.abdim.donationtracker.models;

import android.media.Image;

import java.io.Serializable;
import java.util.Locale;

public class Item implements Serializable {
    String name;
    String description;
    int quantity;
//    Image itemPhoto;
    Location location;
    ItemCategory category;
    String time;
    Double value;

    public Item(String name, String description, int quantity, Image itemPhoto, Location location, ItemCategory category, String time, Double value) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
//        this.itemPhoto = itemPhoto;
        this.location = location;
        this.category = category;
        this.time = time;
        this.value = value;
    }

//    public Image getItemPhoto() {
//        return itemPhoto;
//    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

//    public void setItemPhoto(Image itemPhoto) {
//        this.itemPhoto = itemPhoto;
//    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ItemCategory getCategory() {
        return category;
    }

    public void setCategory(ItemCategory category) {
        this.category = category;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

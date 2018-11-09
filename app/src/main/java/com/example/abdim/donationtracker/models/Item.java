package com.example.abdim.donationtracker.models;
import java.util.Map;
import java.util.HashMap;

public class Item {
    private String id;
    private String name;
    private String description;
    private int quantity;
    private String locationId;
    private ItemCategory category;
    private String time;
    private Double value;

    /**
     * Creates Item object.
     */
    public Item() {
        // default constructor
    }

    public Item(String id, String name, String description, int quantity, String locationId,
                ItemCategory category, String time, Double value) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.locationId = locationId;
        this.category = category;
        this.time = time;
        this.value = value;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("name", name);
        result.put("description", description);
        result.put("quantity", quantity);
        result.put("locationId", locationId);
        result.put("category", category);
        result.put("time", time);
        result.put("value", value);

        return result;
    }


    // getters

    public String getId() { return id; }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Double getValue() {
        return value;
    }

    public String getLocationId() {
        return locationId;
    }

    public ItemCategory getCategory() {
        return category;
    }

    public String getTime() {
        return time;
    }

    // setters

    public void setId(String id) { this.id = id; }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public void setCategory(ItemCategory category) {
        this.category = category;
    }

    public void setTime(String time) {
        this.time = time;
    }


    // toString

    public String toString() {
        return name;
    }

}

package com.example.abdim.donationtracker.models;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;
import java.util.HashMap;

/**
 * Class that represents an Item object
 */
public class Item {
    private static final String TAG = "ItemModel";
    private String id;
    private String name;
    private String description;
    private int quantity;
    private String locationId;
    private String locationName;
    private ItemCategory category;
    private String time;
    private Double value;

    /**
     * Creates Item object.
     */
    public Item() {
        // default constructor
    }

    /**
     * Item
     * @param id id
     * @param name name
     * @param description description
     * @param quantity quantity
     * @param locationId locationId
     * @param category category
     * @param time time
     * @param value value
     */
    @SuppressWarnings("ConstructorWithTooManyParameters")
    public Item(String id, String name, String description, int quantity, String locationId,
                ItemCategory category, String time, Double value) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.locationId = locationId;
        this.quantity = quantity;
        this.category = category;
        this.time = time;
        this.value = value;
        // this.locationName = null;
    }

    /**
     * Method to return map of Item's attributes
     * @return Map of Item's attributes
     */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("name", name);
        result.put("description", description);
        result.put("quantity", quantity);
        // result.put("locationName", locationName);
        result.put("locationId", locationId);
        result.put("category", category);
        result.put("time", time);
        result.put("value", value);

        return result;
    }


    // getters

    /**
     * Item id getter
     * @return String of item id
     */
    public String getId() { return id; }

    /**
     * Item quantity getter
     * @return item quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Item description getter
     * @return description of item
     */
    public String getDescription() {
        return description;
    }

    /**
     * Item locationId getter
     * @return locationId of item
     */
    public String getLocationId() { return locationId; }

    /**
     * Item name getter
     * @return description of name
     */
    public String getName() {
        return name;
    }

    /**
     * Item value getter
     * @return value of item
     */
    public Double getValue() {
        return value;
    }

    /**
     * Item category getter
     * @return category of item
     */
    public ItemCategory getCategory() {
        return category;
    }

    /**
     * Item location name getter
     * @return location name of item
     */
    public String getLocationName() { return locationName; }
    /**
     * Time of item donation getter
     * @return item donation time
     */
    public String getTime() {
        return time;
    }

    // setters

    /**
     * setter for location name
     * @param locationName location name
     */
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
    /**
     * create & set location name
     * @param locationId locationId
     */
    public void createLocationName(String locationId) {
        Log.d(TAG, "getting in here");
        Log.d(TAG, "locationId " + locationId);
        DatabaseReference locationsRef = FirebaseDatabase.getInstance().getReference(
                "locations");
        locationsRef.orderByChild("locationId").equalTo(
                Integer.parseInt(locationId)).addChildEventListener(
                new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                        String locationName = dataSnapshot.getValue(Location.class).getName();
                        try {
                            Log.d(TAG, "locationName is " + dataSnapshot.getValue(
                                    Location.class).getName());
                        } catch (NullPointerException e) {
                            Log.d(TAG, "locationName is null");
                        }
                        Item.this.setLocationName(locationName);
                    }
                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String string) {
                    }
                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String string) {
                    }
                }
        );
    }


    // toString

    /**
     * Item toString
     * @return item's String representation
     */
    public String toString() {
        return name;
    }
}

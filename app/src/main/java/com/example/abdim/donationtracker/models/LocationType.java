package com.example.abdim.donationtracker.models;

public enum LocationType {
//    Dropoff-only, Store (drop-off and sales), Warehouse (inventory storage only)
    DROPOFFONLY("Dropoff-only"),
    STORE("Store"),
    WAREHOUSE("Warehouse");

    private final String name;

    LocationType(String typeName) {
        name = typeName;
    }

    public String toString() {
        return name;
    }
}

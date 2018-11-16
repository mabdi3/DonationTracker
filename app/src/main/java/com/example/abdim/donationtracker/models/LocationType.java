package com.example.abdim.donationtracker.models;
/**
 * Represents a LocationType
 */
public enum LocationType  {
    DROPOFFONLY("DropOff-only"),
    STORE("Store"),
    WAREHOUSE("Warehouse");

    private final String name;

    /**
     * Constructs LocationType.
     * @param typeName the name of the LocationType
     */
    LocationType(String typeName) {
        name = typeName;
    }

    /**
     * @return name of the enumerated LocationType
     */
    public String toString() {
        return name;
    }

}

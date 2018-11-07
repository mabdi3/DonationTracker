package com.example.abdim.donationtracker.models;

import java.io.Serializable;

public enum LocationType implements Serializable {
//    Dropoff-only, Store (drop-off and sales), Warehouse (inventory storage only)
    DROPOFFONLY("Dropoff-only"),
    STORE("Store"),
    WAREHOUSE("Warehouse");

    private final String name;

    LocationType(String typeName) {
        name = typeName;
    }

    /**
     * @return name of the enumerated LocationType
     */
    public String toString() {
        return name;
    }

    /**
     * If it matches, converts and input string into it's corresponding Enum
     * @param str
     * @return the locationType if it matches the input string
     */
    public static LocationType parseLocationType(String str) {
        for (LocationType type : LocationType.values()) {
            if(str.equals(type.toString())) {
                return type;
            }
        }
        return null;
    }
}

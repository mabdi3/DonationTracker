package com.example.abdim.donationtracker.models;
/**
 * Represents a LocationType
 */
public enum LocationType  {
    DROPOFFONLY("Dropoff-only"),
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

    /**
     * If it matches, converts and input string into it's corresponding Enum
     * @param str the string to look for in LocationType.values()
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

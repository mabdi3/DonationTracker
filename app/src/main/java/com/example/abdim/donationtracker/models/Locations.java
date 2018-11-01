package com.example.abdim.donationtracker.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Locations {

    // use this hashset to do O(1) contains
    private static Set<Location> locationsAsHashSet = new HashSet<>();

    // use this because a lot of views need a list, so instead of always converting from hashset to arraylist, just keep an arraylist handy
    private static List<Location> locationsAsList = new ArrayList<>();

    /**
     * Attempts to add a new location to the container. Returns true if added a whole new location.
     * False otherwise.
     *
     *
     */
    public static boolean addLocation(Location newLocation) {
        if (locationsAsHashSet.contains(newLocation)) {
            return false;
        } else {
            locationsAsHashSet.add(newLocation);
            locationsAsList.add(newLocation);
            return true;
        }
    }

    public static Set<Location> getLocationsAsHashSet() {
        return locationsAsHashSet;
    }

    public static void setLocationsAsHashSet(Set<Location> locationsAsHashSet) {
        Locations.locationsAsHashSet = locationsAsHashSet;
    }

    public static List<Location> getLocationsAsList() {
        return locationsAsList;
    }

    public static void setLocationsAsList(List<Location> locationsAsList) {
        Locations.locationsAsList = locationsAsList;
    }
}

package com.example.abdim.donationtracker.models;

import java.util.ArrayList;
import java.util.List;

public class Locations {

    private static List<Location> locations = new ArrayList<>();

    public List<Location> getLocations() {
        return locations;
    }

    public void addLocation(Location newLocal) {
        locations.add(newLocal);
    }
}

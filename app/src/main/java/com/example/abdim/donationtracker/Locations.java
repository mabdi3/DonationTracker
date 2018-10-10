package com.example.abdim.donationtracker;

import java.util.ArrayList;
import java.util.List;

public class Locations {

    private List<Location> locations;

    public Locations() {
        locations = new ArrayList<>();
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void addLocation(Location newLocal) {
        locations.add(newLocal);
    }
}

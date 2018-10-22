package com.example.abdim.donationtracker.models;

import java.util.Objects;

public class Location {

    private int id;
    private String name;
    private LocationType locationType;
    private double longitude;
    private double latitude;
    private String address;
    private String phoneNumber;
    private String websiteLink;
    private ItemList locationItemList = new ItemList();

    public Location() {
        //Default constructor
    }

    public Location(int id, String name, LocationType locationType, double longitude, double latitude, String address, String phoneNumber, String websiteLink) {
        this.id = id;
        this.name = name;
        this.locationType = locationType;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.websiteLink = websiteLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocationType getLocationType() {
        return locationType;
    }

    public void setLocationType(LocationType locationType) {
        this.locationType = locationType;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebsiteLink() {
        return websiteLink;
    }

    public void setWebsiteLink(String websiteLink) {
        this.websiteLink = websiteLink;
    }

    public String toString() {
        return name;
    }

    public void addItem(Item item) {
        locationItemList.addItem(item);
    }

    public void addDonation (Donation donation) {
        for (Item i : donation.getItems().getItemList()) {
            addItem(i);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return id == location.id &&
                Double.compare(location.longitude, longitude) == 0 &&
                Double.compare(location.latitude, latitude) == 0 &&
                Objects.equals(name, location.name) &&
                locationType == location.locationType &&
                Objects.equals(address, location.address) &&
                Objects.equals(phoneNumber, location.phoneNumber) &&
                Objects.equals(websiteLink, location.websiteLink) &&
                Objects.equals(locationItemList, location.locationItemList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, locationType, longitude, latitude, address, phoneNumber, websiteLink, locationItemList);
    }
}

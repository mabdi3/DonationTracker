package com.example.abdim.donationtracker.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Objects;
import java.util.Map;
import java.util.HashMap;

public class Location implements Serializable {

    private int locationId;
    private String name;
    private LocationType LocationType;
    private double longitude;
    private double latitude;
    private String address;
    private String phoneNumber;
    private String websiteLink;
    private ItemList locationItemList;

    public Location() {
        //Default constructor
    }

    public Location(int id, String name, LocationType LocationType, double longitude, double latitude, String address, String phoneNumber, String websiteLink) {
        this.locationId = id;
        this.name = name;
        this.LocationType = LocationType;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.websiteLink = websiteLink;
        this.locationItemList = new ItemList();
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("locationId", locationId);
        result.put("name", name);
        result.put("LocationType", LocationType);
        result.put("longitude", longitude);
        result.put("latitude", latitude);
        result.put("address", address);
        result.put("phoneNumber", phoneNumber);
        result.put("websiteLink", websiteLink);
        result.put("locationItemList", locationItemList);

        return result;
    }

    // getters

    public int getLocationId() {
        return locationId;
    }

    public String getName() {
        return name;
    }

    public LocationType getLocationType() {
        return LocationType;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getAddress() {
        return address;
    }

    public ItemList getLocationItemList() {
        return locationItemList;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getWebsiteLink() {
        return websiteLink;
    }

    // setters

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocationType(LocationType LocationType) {
        this.LocationType = LocationType;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLocationItemList(ItemList locationItemList) {
        this.locationItemList = locationItemList;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
        return locationId == location.locationId &&
                Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(locationId, name);
    }
}

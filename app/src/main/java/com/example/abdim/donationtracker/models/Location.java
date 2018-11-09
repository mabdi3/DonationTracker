package com.example.abdim.donationtracker.models;

import java.util.Objects;
import java.util.Map;
import java.util.HashMap;

public class Location {

    private int locationId;
    private String name;
    private LocationType LocationType;
    private double longitude;
    private double latitude;
    private String address;
    private String phoneNumber;
    private String websiteLink;
    private ItemList locationItemList;

    /**
     * Constructs location object leaving attributes null.
     */
    public Location() {
        //Default constructor
    }

    /**
     * Constructs location object.
     * @param id an integer representing the location's ID
     * @param name name of the location
     * @param LocationType enum type of location
     * @param longitude longitudinal coordinate of the location
     * @param latitude latitudinal coordinate of the location
     * @param address string address of the location
     * @param phoneNumber location's phone number
     * @param websiteLink link to location's website
     */
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

    /**
     * Converts attributes of location into a HashMap.
     * @return HashMap representing the location
     */
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

    /**
     * Returns this location's ID
     * @return the Location's ID number
     */
    public int getLocationId() {
        return locationId;
    }
    /**
     * Returns this location's Name
     * @return the Location's Name
     */
    public String getName() {
        return name;
    }
    /**
     * Returns this location's enumerated LocationType
     * @return the Location's type
     */
    public LocationType getLocationType() {
        return LocationType;
    }
    /**
     * Returns this location's longitude
     * @return the Location's longitude
     */
    public double getLongitude() {
        return longitude;
    }
    /**
     * Returns this location's latitude
     * @return the Location's latitude
     */
    public double getLatitude() {
        return latitude;
    }
    /**
     * Returns this location's Address
     * @return the Location's Address
     */
    public String getAddress() {
        return address;
    }
    /**
     * Returns this location's list of items
     * @return the Location's list of items
     */
    public ItemList getLocationItemList() {
        return locationItemList;
    }
    /**
     * Returns this location's phone number
     * @return the Location's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * Returns this location's website link
     * @return the Location's website url
     */
    public String getWebsiteLink() {
        return websiteLink;
    }

    // setters

    /**
     * sets locationID
     * @param locationId integer value for the new ID
     */
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
    /**
     * sets new Name
     * @param name string value for the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * sets Location type
     * @param LocationType enum defining what kind of location this instance is
     */
    public void setLocationType(LocationType LocationType) {
        this.LocationType = LocationType;
    }

    /**
     * sets the location's latitude
     * @param longitude double value of the longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * sets the location's latitude
     * @param latitude double value of the latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * sets the location's address
     * @param address string of the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets a list of items to serve as this location's inventory
     * @param locationItemList list of items
     */
    public void setLocationItemList(ItemList locationItemList) {
        this.locationItemList = locationItemList;
    }

    /**
     * return's the locations phone number
     * @param phoneNumber location's phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * sets the Location's website url
     * @param websiteLink url of the new website
     */
    public void setWebsiteLink(String websiteLink) {
        this.websiteLink = websiteLink;
    }

    /**
     * Returns the location's name
     * @return name
     */
    public String toString() {
        return name;
    }

    /**
     * Adds item to this Location's inventory
     * @param item
     */
    public void addItem(Item item) {
        locationItemList.addItem(item);
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

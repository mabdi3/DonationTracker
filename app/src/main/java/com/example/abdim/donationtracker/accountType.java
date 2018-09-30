package com.example.abdim.donationtracker;

public enum accountType {
    User("User"), Location_Employee("Location Employee"), Manager("Manager");
    private final String name;

    accountType(String typeName) {
        name = typeName;
    }
    public String toString() {
        return name;
    }
}

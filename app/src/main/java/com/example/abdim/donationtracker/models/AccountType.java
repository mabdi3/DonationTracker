package com.example.abdim.donationtracker.models;

public enum AccountType {
    User("User"),
    Location_Employee("Location Employee"),
    Manager("Manager"),
    Admin("Admin");

    private final String name;

    AccountType(String typeName) {
        name = typeName;
    }
    public String toString() {
        return name;
    }
}

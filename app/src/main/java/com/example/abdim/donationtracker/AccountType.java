package com.example.abdim.donationtracker;

public enum AccountType {
    User("User"), Location_Employee("Location Employee"), Manager("Manager");
    private final String name;

    AccountType(String typeName) {
        name = typeName;
    }
    public String toString() {
        return name;
    }
}

package com.example.abdim.donationtracker.models;

import java.io.Serializable;

public enum AccountType implements Serializable {
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

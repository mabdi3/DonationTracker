package com.example.abdim.donationtracker.models;

public enum AccountType {
    User("User"),
    Location_Employee("Location Employee"),
    Manager("Manager"),
    Admin("Admin");

    private final String name;

    /**
     * Creates AccountType object for tracking an account's type.
     * @param typeName string representation of the AccountType object
     */
    AccountType(String typeName) {
        name = typeName;
    }

    /**
     * String representation of the enum
     * @return name
     */
    public String toString() {
        return name;
    }
}

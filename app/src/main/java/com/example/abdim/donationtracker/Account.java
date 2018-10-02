package com.example.abdim.donationtracker;

public class Account {
    public String username;
    private String password;
    AccountType userType;

    Account(String userIn, String pass, AccountType ut) {
        username = userIn;
        password = pass;
        userType = ut;
    }
    public String getUsername() {
        return username;
    }
    public String getPass() {
        return password;
    }
    public String getType() {
        return userType.toString();
    }
    public AccountType getTypeEnum() {
        return userType;
    }
    public String toString() {
        return username + " has the password: " + password + " and is a: " + userType;
    }
}

package com.example.abdim.donationtracker.models;

import java.io.Serializable;

public class Account implements Serializable {
    public String username;
    private String email;
    private String password;
    AccountType userType;

    public Account(String userIn, String passIn, AccountType ut, String emailIn) {
        username = userIn;
        password = passIn;
        email = emailIn;
        userType = ut;
    }

    public Account(String userIn, String passIn, AccountType ut) {
        this(userIn, passIn, ut, "");
    }

    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
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

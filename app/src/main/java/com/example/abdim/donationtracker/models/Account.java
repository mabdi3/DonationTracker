package com.example.abdim.donationtracker.models;

/**
 * Represents an Account object
 */
public class Account {
    private String username;
    private String email;
    private String pass;
    private AccountType type;

    /**
     * Creates an account object
     */
    public Account() {

    }

    /**
     * Creates an account object
     * @param userIn Input username
     * @param passIn Input password
     * @param ut accountType
     * @param emailIn input email address
     */
    public Account(String userIn, String passIn, AccountType ut, String emailIn) {
        username = userIn;
        pass = passIn;
        email = emailIn;
        type = ut;
    }

    /**
     * Creates an account object, but leaves email empty
     * @param userIn username
     * @param passIn password
     * @param ut account type
     */
    public Account(String userIn, String passIn, AccountType ut) {
        this(userIn, passIn, ut, "");
    }

    // getters

    /**
     * Getter for accountType
     * @return type
     */
    public AccountType getType() {
        return type;
    }

    // setters


    /**
     * @return the string representation of the Account
     */
    public String toString() {
        return username + " has the password: " + pass + " and is a: " + type;
    }
}

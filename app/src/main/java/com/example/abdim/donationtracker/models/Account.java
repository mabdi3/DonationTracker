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
     * @param ut accounttype
     */
    public Account(String userIn, String passIn, AccountType ut) {
        this(userIn, passIn, ut, "");
    }

    // getters

    /**
     * Getter for username
     * @return usename
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter for email address
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter for password
     * @return password
     */
    public String getPass() {
        return pass;
    }

    /**
     * Getter for accountType
     * @return type
     */
    public AccountType getType() {
        return type;
    }

    // setters

    /**
     * Sets username for account
     * @param username username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets email for account
     * @param email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets password for account
     * @param pass poassword
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * Sets account type
     * @param type type of account
     */
    public void setType(AccountType type) {
        this.type = type;
    }

    /**
     * @return the string representation of the Account
     */
    public String toString() {
        return username + " has the password: " + pass + " and is a: " + type;
    }
}

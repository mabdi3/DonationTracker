package com.example.abdim.donationtracker.models;

public class Account {
    public String username;
    private String email;
    private String pass;
    private AccountType type;

    /**
     * Creates Account item, leaving attributes blank.
     */
    public Account() {

    }

    /**
     * Creates Account item.
     * @param userIn userName input
     * @param passIn password input
     * @param ut user type input
     * @param emailIn email input
     */
    public Account(String userIn, String passIn, AccountType ut, String emailIn) {
        username = userIn;
        pass = passIn;
        email = emailIn;
        type = ut;
    }

    /**
     * Constructs Account item and leaves email blank
     * @param userIn username input
     * @param passIn password input
     * @param ut user type input
     */
    public Account(String userIn, String passIn, AccountType ut) {
        this(userIn, passIn, ut, "");
    }

    /**
     * Returns account's username.
     * @return account's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns account's email.
     * @return account's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns account's password.
     * @return account's password
     */
    public String getPass() {
        return pass;
    }

    /**
     * Returns account's user type.
     * @return account's user type
     */
    public AccountType getType() {
        return type;
    }

    /**
     * Sets account's username.
     * @param username the string to assign the account's username to
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets account's email.
     * @param email the string to assign the account's email to
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets account's password
     * @param pass the string to assign the account's password to
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * Set's account's type
     * @param type the string to assign the account's type to
     */
    public void setType(AccountType type) {
        this.type = type;
    }

    /**
     * Returns a string representation of the account.
     * @return the account as a string
     */
    public String toString() {
        return username + " has the password: " + pass + " and is a: " + type;
    }
}

package com.example.abdim.donationtracker.models;

public class Account {
    public String username;
    private String email;
    private String pass;
    private AccountType type;


    public Account() {

    }

    public Account(String userIn, String passIn, AccountType ut, String emailIn) {
        username = userIn;
        pass = passIn;
        email = emailIn;
        type = ut;
    }

    public Account(String userIn, String passIn, AccountType ut) {
        this(userIn, passIn, ut, "");
    }

    // getters
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String getPass() {
        return pass;
    }
    public AccountType getType() {
        return type;
    }

    // setters
    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public void setType(AccountType type) {
        this.type = type;
    }

    public String toString() {
        return username + " has the password: " + pass + " and is a: " + type;
    }
}

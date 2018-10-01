package com.example.abdim.donationtracker;

import java.util.ArrayList;

public class RegisteredAccounts {
    public static ArrayList<Account> accountStorage;

    public static void addAccount(Account addedAccount) {
        accountStorage.add(addedAccount);
    }
    //TODO 4 make this compare the username, password, and account types independently instead of just a .contains
    public static boolean accountExists(Account existAccount) {
        return accountStorage.contains(existAccount);

    }
}

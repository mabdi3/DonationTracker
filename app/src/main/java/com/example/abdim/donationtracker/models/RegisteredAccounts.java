package com.example.abdim.donationtracker.models;

import android.util.Log;

import com.example.abdim.donationtracker.models.Account;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class RegisteredAccounts {
    private static ArrayList<Account> accountStorage = new ArrayList<>(Collections.singletonList(new Account("1234", "1234", AccountType.Admin)));

    public static void addAccount(Account addedAccount) {
        accountStorage.add(addedAccount);
    }

    public static ArrayList<Account> getAccountStorage() {
        return accountStorage;
    }

    public static void printData(){
        for (Account count : accountStorage) {
            Log.d("Register", "Adding: " + count.toString());
        }
    }


    /**
     * Checks if {existAccount} is already logged in the system.
     *
     * @param existAccount Account to see if in system
     * @return true if {existAccount} is in system
     */
    public static boolean accountExists(Account existAccount) {
        for (Account account : accountStorage) {
            if (existAccount.getUsername().equals(account.getUsername()) &&
                existAccount.getPass().equals(account.getPass()) &&
                existAccount.getTypeEnum() == account.getTypeEnum()) {
                return true;
            }
        }
        return false;
    }
}

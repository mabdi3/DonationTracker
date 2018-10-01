package com.example.abdim.donationtracker;

import java.util.ArrayList;

public class RegisteredAccounts {
    private static ArrayList<Account> accountStorage;

    public static void addAccount(Account addedAccount) {
        accountStorage.add(addedAccount);
    }

    public static ArrayList<Account> getAccountStorage() {
        return accountStorage;
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

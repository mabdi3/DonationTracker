package com.example.abdim.donationtracker;

import com.example.abdim.donationtracker.controllers.MainActivity;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * Jerome White's UnitTest to test the MainActivity's check for valid username and password
 */
public class JeromeUnitTest {

    private MainActivity mainActivity;
    private String badUsername;
    private String badPassword;
    private String goodUsername;
    private String goodPassword;

    /**
     * Runs the setup for each test.
     */
    @Before
    public void setup() {
        mainActivity = new MainActivity();
        badUsername = "";
        badPassword = "";
        goodUsername = "email@gmail.com";
        goodPassword = "password";
    }

    /**
     * Checks for when either argument is null.
     */
    @Test
    public void nullUsernameOrNullPassword() {
        assertFalse(mainActivity.validateForm(null, goodPassword));
        assertFalse(mainActivity.validateForm(goodUsername, null));
        assertFalse(mainActivity.validateForm(null, null));
    }


    /**
     * Checks for when both username and password are valid
     */
    @Test
    public void goodUsernameAndGoodPassword() {
        assertTrue(mainActivity.validateForm(goodUsername, goodPassword));
    }

    /**
     * Checks for when both username and password are invalid
     */
    @Test
    public void badUsernameAndBadPassword() {
        assertFalse(mainActivity.validateForm(badUsername, badPassword));
    }

    /**
     * Checks for when only the password is valid
     */
    @Test
    public void badUsernameAndGoodPassword() {
        assertFalse(mainActivity.validateForm(badUsername, goodPassword));
    }

    /**
     * Checks for when only the username is valid
     */
    @Test
    public void goodUsernameAndBadPassword() {
        assertFalse(mainActivity.validateForm(goodUsername, badPassword));
    }


}

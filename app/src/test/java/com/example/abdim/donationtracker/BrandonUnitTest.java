package com.example.abdim.donationtracker;

import com.example.abdim.donationtracker.controllers.RegisterActivity;

import org.junit.Before;
import org.junit.Test;

//import java.util.Random;

import static org.junit.Assert.*;

/**
 * Brandon Vu's UnitTest to test the RegisterActivity's check for valid username and password
 */
public class BrandonUnitTest {

    private RegisterActivity registerActivity;
    private String badUsername;
    private String badPassword;
    private String goodUsername;
    private String goodPassword;

    /**
     * Runs the setup for each test.
     */
    @Before
    public void setup() {
        registerActivity = new RegisterActivity();
        badUsername = "";
        badPassword = "";
        goodUsername = "";
        goodPassword = "";

//        //criteria for good username is must have "@" and then a "." afterwards at some point, so
//
//        Random rand = new Random(18);
//        int nameBound = rand.nextInt(100);
//        for (int i = 0; i < nameBound; i++) {
//            badUsername += Integer.toString(rand.nextInt(10));
//            goodUsername += Integer.toString(rand.nextInt(10));
//        }
//        goodUsername += "@gmail.com";
//
//        //criteria for good password is must have length of at least 6
//        int minLength = registerActivity.MIN_PASS_LENGTH;
//        for (int i = 0; i < minLength - 1; i++) {
//            badPassword += Integer.toString(rand.nextInt(10));
//            goodPassword += Integer.toString(rand.nextInt(10));
//        }
//
//        goodPassword += Integer.toString(rand.nextInt(10));

        badUsername = "1231234@gmail";
        goodUsername = "123421@gmail.com";
        badPassword = "12345";
        goodPassword = "12312412412";
    }

    /**
     * Checks for when either argument is null.
     */
    @Test
    public void nullUsernameOrNullPassword() {
        assertFalse(callRegisterActivityValidTest(null, goodPassword));
        assertFalse(callRegisterActivityValidTest(goodUsername, null));
        assertFalse(callRegisterActivityValidTest(null, null));
    }

    /**
     * Checks for when both username and password are valid
     */
    @Test
    public void goodUsernameAndGoodPassword() {
        assertTrue(callRegisterActivityValidTest(goodUsername, goodPassword));
    }

    /**
     * Checks for when both username and password are invalid
     */
    @Test
    public void badUsernameAndBadPassword() {
        assertFalse(callRegisterActivityValidTest(badUsername, badPassword));
    }

    /**
     * Checks for when only the password is valid
     */
    @Test
    public void badUsernameAndGoodPassword() {
        assertFalse(callRegisterActivityValidTest(badUsername, goodPassword));
    }

    /**
     * Checks for when only the username is valid
     */
    @Test
    public void goodUsernameAndBadPassword() {
        assertFalse(callRegisterActivityValidTest(goodUsername, badPassword));
    }

    /**
     * Helper method to get rid of feature envy thanks lint
     *
     * @param username username to check
     * @param password password to check
     * @return true if username and password are both valid
     */
    private boolean callRegisterActivityValidTest(CharSequence username, CharSequence password) {
        return registerActivity.usernameAndPassIsValid(username, password);
    }

}

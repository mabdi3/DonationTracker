package com.example.abdim.donationtracker;

import com.example.abdim.donationtracker.controllers.RegisterActivity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BrandonUnitTest {

    RegisterActivity registerActivity;
    String badUsername;
    String badPassword;
    String goodUsername;
    String goodPassword;

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

    @Test
    public void nullUsernameOrNullPassword() {
        assertFalse(registerActivity.usernameAndPassIsValid(null, goodPassword));
        assertFalse(registerActivity.usernameAndPassIsValid(goodUsername, null));
        assertFalse(registerActivity.usernameAndPassIsValid(null, null));
    }

    @Test
    public void goodUsernameAndGoodPassword() {
        assertTrue(registerActivity.usernameAndPassIsValid(goodUsername, goodPassword));
    }

    @Test
    public void badUsernameAndBadPassword() {
        assertFalse(registerActivity.usernameAndPassIsValid(badUsername, badPassword));
    }

    @Test
    public void badUsernameAndGoodPassword() {
        assertFalse(registerActivity.usernameAndPassIsValid(badUsername, goodPassword));
    }

    @Test
    public void goodUsernameAndBadPassword() {
        assertFalse(registerActivity.usernameAndPassIsValid(goodUsername, badPassword));
    }

}

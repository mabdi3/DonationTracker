package com.example.abdim.donationtracker;

import com.example.abdim.donationtracker.controllers.MainActivity;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;
public class JeromeUnitTest {

    MainActivity mainActivity;
    String badUsername;
    String badPassword;
    String goodUsername;
    String goodPassword;

    @Before
    public void setup() {
        mainActivity = new MainActivity();
        badUsername = "";
        badPassword = "";
        goodUsername = "email@gmail.com";
        goodPassword = "password";
    }

    @Test
    public void nullUsernameOrNullPassword() {
        assertFalse(mainActivity.validateForm(null, goodPassword));
        assertFalse(mainActivity.validateForm(goodUsername, null));
        assertFalse(mainActivity.validateForm(null, null));
    }

    @Test
    public void goodUsernameAndGoodPassword() {
        assertTrue(mainActivity.validateForm(goodUsername, goodPassword));
    }

    @Test
    public void badUsernameAndBadPassword() {
        assertFalse(mainActivity.validateForm(badUsername, badPassword));
    }

    @Test
    public void badUsernameAndGoodPassword() {
        assertFalse(mainActivity.validateForm(badUsername, goodPassword));
    }

    @Test
    public void goodUsernameAndBadPassword() {
        assertFalse(mainActivity.validateForm(goodUsername, badPassword));
    }


}

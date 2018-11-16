package com.example.abdim.donationtracker;

/**
 * @author Mohamed Abdi
 */
import com.example.abdim.donationtracker.controllers.AddItemActivity;
import com.example.abdim.donationtracker.models.Account;
import com.example.abdim.donationtracker.models.ItemCategories;
import com.example.abdim.donationtracker.models.ItemCategory;
import com.example.abdim.donationtracker.models.LocationType;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
public class ItemTest {

    @Test
    public void itemTest() {
        assertFalse(AddItemActivity.validateItem(null));
        assertTrue(AddItemActivity.validateItem("testItem"));
    }
}

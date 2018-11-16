package com.example.abdim.donationtracker;

/**
 * @author Mohamed Abdi
 */
import com.example.abdim.donationtracker.controllers.AddItemActivity;
import com.example.abdim.donationtracker.controllers.ViewItemActivity;
import com.example.abdim.donationtracker.models.Account;
import com.example.abdim.donationtracker.models.Item;
import com.example.abdim.donationtracker.models.ItemCategories;
import com.example.abdim.donationtracker.models.ItemCategory;
import com.example.abdim.donationtracker.models.LocationType;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class LeweyUnitTest {
    Item nintendo3ds = new Item(
            "1",
            "Red Nintendo 3DS",
            "It is good, and nice, and I like it",
            1,
            "Newnan",
            ItemCategories.getItemCategoriesAsList().get(1),
            "2:00",
            129.99
    );
    Item fakeNintendo3ds = new Item(
            "1",
            null,
            "It is good, and nice, and I like it",
            1,
            "Newnan",
            ItemCategories.getItemCategoriesAsList().get(1),
            "2:00",
            129.99
    );
    Item literallyFake = new Item(
            null,
            null,
            null,
            1,
            null,
            null,
            null,
            129.99
    );
    @Test
    public void displayTestPass() {
        assertTrue(ViewItemActivity.checkIfValidForDisplay(nintendo3ds));
    }
    @Test
    public void displayTestOneNull() {
        assertFalse(ViewItemActivity.checkIfValidForDisplay(fakeNintendo3ds));
    }
    @Test
    public void displayTestAllNull() {
        assertFalse(ViewItemActivity.checkIfValidForDisplay(literallyFake));
    }

}

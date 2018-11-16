package com.example.abdim.donationtracker;

/**
 * @author Mohamed Abdi
 */
import com.example.abdim.donationtracker.models.ItemCategories;
import com.example.abdim.donationtracker.models.ItemCategory;
import com.example.abdim.donationtracker.models.LocationType;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
public class ItemCategoryTest {

    @Test
    public void itemCategory() {
        ItemCategories items = new ItemCategories();
        items.addItemCategory(new ItemCategory("god"));
        assertFalse("Should be false", items.addItemCategory(new ItemCategory("god")));


        items.getItemCategoriesAsList().remove(0);
        items.getItemCategoriesAsHashSet().remove(new ItemCategory("god"));
        assertEquals(0, items.getItemCategoriesAsList().size());
        assertTrue("Should be true", items.addItemCategory(new ItemCategory("god")));
    }
}

package com.example.abdim.donationtracker;

/**
 * @author Lewey Wilson
 */
import com.example.abdim.donationtracker.models.LocationType;

import org.junit.Test;

import static org.junit.Assert.*;
public class ParseLocationTypeTest {

    @Test
    public void parseLocationTrue() {
        String locationType = "Dropoff-only";
        assertEquals(LocationType.DROPOFFONLY, LocationType.parseLocationType(locationType));
    }
    @Test
    public void parseLocationFalse() {
        String locationType = "DROPOFF";
        assertNotEquals(LocationType.DROPOFFONLY, LocationType.parseLocationType(locationType));
    }
    @Test
    public void parseLocationNull() {
        String locationType = null;
        assertEquals(null, LocationType.parseLocationType(locationType));
    }
}

package com.codeprep;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class BuilderDemoTest {

    // ✅ Test 1: Build a house with all required fields
    @Test
    public void testBuildHouseWithRequiredFields() {
        House house = new House.HouseBuilder("Concrete", "Brick", "Tiles")
                .build();

        assertNotNull(house);
        assertTrue(house.toString().contains("Concrete"));
        assertTrue(house.toString().contains("Brick"));
        assertTrue(house.toString().contains("Tiles"));
    }

    // ✅ Test 2: Build a house with all fields including optional ones
    @Test
    public void testBuildHouseWithAllFields() {
        House house = new House.HouseBuilder("Concrete", "Brick", "Tiles")
                .setGarage(true)
                .setSwimmingPool(true)
                .setGarden(true)
                .build();

        assertNotNull(house);
        assertTrue(house.toString().contains("garage=true"));
        assertTrue(house.toString().contains("swimmingPool=true"));
        assertTrue(house.toString().contains("garden=true"));
    }

    // ✅ Test 3: Optional fields default to false when not set
    @Test
    public void testOptionalFieldsDefaultToFalse() {
        House house = new House.HouseBuilder("Concrete", "Brick", "Tiles")
                .build();

        assertTrue(house.toString().contains("garage=false"));
        assertTrue(house.toString().contains("swimmingPool=false"));
        assertTrue(house.toString().contains("garden=false"));
    }

    // ✅ Test 4: Null foundation throws IllegalStateException
    @Test(expected = IllegalStateException.class)
    public void testNullFoundationThrowsException() {
        new House.HouseBuilder(null, "Brick", "Tiles").build();
    }

    // ✅ Test 5: Null structure throws IllegalStateException
    @Test(expected = IllegalStateException.class)
    public void testNullStructureThrowsException() {
        new House.HouseBuilder("Concrete", null, "Tiles").build();
    }

    // ✅ Test 6: Null roof throws IllegalStateException
    @Test(expected = IllegalStateException.class)
    public void testNullRoofThrowsException() {
        new House.HouseBuilder("Concrete", "Brick", null).build();
    }

    // ✅ Test 7: Empty foundation throws IllegalStateException
    @Test(expected = IllegalStateException.class)
    public void testEmptyFoundationThrowsException() {
        new House.HouseBuilder("", "Brick", "Tiles").build();
    }

    // ✅ Test 8: Empty structure throws IllegalStateException
    @Test(expected = IllegalStateException.class)
    public void testEmptyStructureThrowsException() {
        new House.HouseBuilder("Concrete", "", "Tiles").build();
    }

    // ✅ Test 9: Empty roof throws IllegalStateException
    @Test(expected = IllegalStateException.class)
    public void testEmptyRoofThrowsException() {
        new House.HouseBuilder("Concrete", "Brick", "").build();
    }

    // ✅ Test 10: toString() output is correct
    @Test
    public void testToString() {
        House house = new House.HouseBuilder("Concrete", "Brick", "Tiles")
                .setGarage(true)
                .setSwimmingPool(false)
                .setGarden(true)
                .build();

        String expected = "House{foundation='Concrete', structure='Brick', roof='Tiles', garage=true, swimmingPool=false, garden=true}";
        assertEquals(expected, house.toString());
    }
}

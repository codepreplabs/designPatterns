package com.codeprep;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Cycling State Tests")
class CyclingTest {

    private Cycling cycling;

    @BeforeEach
    void setUp() {
        cycling = new Cycling();
    }

    @Test
    @DisplayName("Should return a positive ETA for cycling")
    void testCalculateETAIsPositive() {
        double eta = cycling.calculateETA();
        assertTrue(eta > 0, "ETA should be positive");
    }

    @Test
    @DisplayName("Should return ETA of 5 for cycling")
    void testCalculateETAValue() {
        assertEquals(5.0, cycling.calculateETA(), "Cycling ETA should be 5");
    }

    @Test
    @DisplayName("Cycling ETA should be less than walking ETA")
    void testCyclingFasterThanWalking() {
        Walking walking = new Walking();
        assertTrue(cycling.calculateETA() < walking.calculateETA(),
                "Cycling ETA should be shorter than walking ETA");
    }

    @Test
    @DisplayName("Should return a non-null direction for cycling")
    void testGetDirectionNotNull() {
        assertNotNull(cycling.getDirection(), "Direction should not be null");
    }

    @Test
    @DisplayName("Should return a non-empty direction for cycling")
    void testGetDirectionNotEmpty() {
        assertFalse(cycling.getDirection().isBlank(), "Direction should not be empty");
    }

    @Test
    @DisplayName("Should return cycling-specific direction string")
    void testGetDirectionContent() {
        assertTrue(cycling.getDirection().toLowerCase().contains("cycling"),
                "Direction should mention cycling");
    }
}


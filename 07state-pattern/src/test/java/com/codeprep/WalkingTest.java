package com.codeprep;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Walking State Tests")
class WalkingTest {

    private Walking walking;

    @BeforeEach
    void setUp() {
        walking = new Walking();
    }

    @Test
    @DisplayName("Should return a positive ETA for walking")
    void testCalculateETAIsPositive() {
        double eta = walking.calculateETA();
        assertTrue(eta > 0, "ETA should be positive");
    }

    @Test
    @DisplayName("Should return ETA of 10 for walking")
    void testCalculateETAValue() {
        assertEquals(10.0, walking.calculateETA(), "Walking ETA should be 10");
    }

    @Test
    @DisplayName("Should return a non-null direction for walking")
    void testGetDirectionNotNull() {
        assertNotNull(walking.getDirection(), "Direction should not be null");
    }

    @Test
    @DisplayName("Should return a non-empty direction for walking")
    void testGetDirectionNotEmpty() {
        assertFalse(walking.getDirection().isBlank(), "Direction should not be empty");
    }

    @Test
    @DisplayName("Should return walking-specific direction string")
    void testGetDirectionContent() {
        assertTrue(walking.getDirection().toLowerCase().contains("walking"),
                "Direction should mention walking");
    }
}


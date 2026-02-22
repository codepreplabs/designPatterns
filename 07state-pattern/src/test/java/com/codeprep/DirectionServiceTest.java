package com.codeprep;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DirectionService (Context) Tests")
class DirectionServiceTest {

    private DirectionService directionService;

    @BeforeEach
    void setUp() {
        directionService = new DirectionService(new Walking());
    }

    // --- Initial state: Walking ---

    @Test
    @DisplayName("Should return walking ETA when initialised with Walking state")
    void testInitialStateETA() {
        assertEquals(10.0, directionService.getETA(),
                "Initial ETA should match Walking state");
    }

    @Test
    @DisplayName("Should return walking direction when initialised with Walking state")
    void testInitialStateDirection() {
        assertTrue(directionService.getDirection().toLowerCase().contains("walking"),
                "Initial direction should be for walking");
    }

    // --- State transition: Walking -> Cycling ---

    @Test
    @DisplayName("ETA should change after switching state to Cycling")
    void testETAChangesAfterStateSwitch() {
        double walkingETA = directionService.getETA();

        directionService.setTransportationMode(new Cycling());
        double cyclingETA = directionService.getETA();

        assertNotEquals(walkingETA, cyclingETA,
                "ETA should differ between Walking and Cycling states");
    }

    @Test
    @DisplayName("Direction should change after switching state to Cycling")
    void testDirectionChangesAfterStateSwitch() {
        directionService.setTransportationMode(new Cycling());

        assertTrue(directionService.getDirection().toLowerCase().contains("cycling"),
                "Direction should update to cycling after state switch");
    }

    @Test
    @DisplayName("Cycling ETA should be lower than Walking ETA")
    void testCyclingETALowerThanWalking() {
        double walkingETA = directionService.getETA();

        directionService.setTransportationMode(new Cycling());
        double cyclingETA = directionService.getETA();

        assertTrue(cyclingETA < walkingETA,
                "Cycling ETA should be shorter than Walking ETA");
    }

    // --- State transition: Cycling -> Walking ---

    @Test
    @DisplayName("Should correctly switch back from Cycling to Walking")
    void testSwitchBackToWalking() {
        directionService.setTransportationMode(new Cycling());
        directionService.setTransportationMode(new Walking());

        assertEquals(10.0, directionService.getETA(),
                "ETA should revert to Walking value after switching back");
        assertTrue(directionService.getDirection().toLowerCase().contains("walking"),
                "Direction should revert to walking after switching back");
    }

    // --- Null safety ---

    @Test
    @DisplayName("Should throw an exception when state is set to null")
    void testSetNullStateShouldThrow() {
        directionService.setTransportationMode(null);

        assertThrows(NullPointerException.class, () -> directionService.getETA(),
                "Should throw NullPointerException when state is null");
    }
}


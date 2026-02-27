package com.codeprep;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactoryPatternDemoTest {

    // --- Enum-based factory tests ---

    @Test
    void testCreateCarWithEnum() {
        Transport transport = TransportFactory.createTransport(TransportType.CAR);
        assertNotNull(transport);
        assertInstanceOf(Car.class, transport);
    }

    @Test
    void testCreateBikeWithEnum() {
        Transport transport = TransportFactory.createTransport(TransportType.BIKE);
        assertNotNull(transport);
        assertInstanceOf(Bike.class, transport);
    }

    @Test
    void testCreateTruckWithEnum() {
        Transport transport = TransportFactory.createTransport(TransportType.TRUCK);
        assertNotNull(transport);
        assertInstanceOf(Truck.class, transport);
    }

    @Test
    void testNullEnumThrowsException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> TransportFactory.createTransport((TransportType) null));
        assertEquals("Transport type cannot be null", ex.getMessage());
    }

    // --- String-based factory tests ---

    @Test
    void testCreateCarWithString() {
        Transport transport = TransportFactory.createTransport("car");
        assertNotNull(transport);
        assertInstanceOf(Car.class, transport);
    }

    @Test
    void testCreateCarWithStringCaseInsensitive() {
        Transport transport = TransportFactory.createTransport("CAR");
        assertNotNull(transport);
        assertInstanceOf(Car.class, transport);
    }

    @Test
    void testCreateBikeWithString() {
        Transport transport = TransportFactory.createTransport("bike");
        assertNotNull(transport);
        assertInstanceOf(Bike.class, transport);
    }

    @Test
    void testCreateTruckWithString() {
        Transport transport = TransportFactory.createTransport("truck");
        assertNotNull(transport);
        assertInstanceOf(Truck.class, transport);
    }

    @Test
    void testInvalidStringThrowsException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> TransportFactory.createTransport("plane"));
        assertTrue(ex.getMessage().contains("Invalid transport type"));
    }

    @Test
    void testNullStringThrowsException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> TransportFactory.createTransport((String) null));
        assertEquals("Transport type cannot be null", ex.getMessage());
    }

    // --- Each transport produces a distinct instance ---

    @Test
    void testFactoryReturnsNewInstanceEachTime() {
        Transport t1 = TransportFactory.createTransport(TransportType.CAR);
        Transport t2 = TransportFactory.createTransport(TransportType.CAR);
        assertNotSame(t1, t2);
    }
}

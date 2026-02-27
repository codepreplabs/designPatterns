package com.codeprep;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class FactoryPatternDemoTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public FactoryPatternDemoTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( FactoryPatternDemoTest.class );
    }

    // --- Enum-based factory tests ---

    public void testCreateCarWithEnum() {
        Transport transport = TransportFactory.createTransport(TransportType.CAR);
        assertNotNull(transport);
        assertTrue(transport instanceof Car);
    }

    public void testCreateBikeWithEnum() {
        Transport transport = TransportFactory.createTransport(TransportType.BIKE);
        assertNotNull(transport);
        assertTrue(transport instanceof Bike);
    }

    public void testCreateTruckWithEnum() {
        Transport transport = TransportFactory.createTransport(TransportType.TRUCK);
        assertNotNull(transport);
        assertTrue(transport instanceof Truck);
    }

    public void testNullEnumThrowsException() {
        try {
            TransportFactory.createTransport((TransportType) null);
            fail("Expected IllegalArgumentException for null enum");
        } catch (IllegalArgumentException e) {
            assertEquals("Transport type cannot be null", e.getMessage());
        }
    }

    // --- String-based factory tests ---

    public void testCreateCarWithString() {
        Transport transport = TransportFactory.createTransport("car");
        assertNotNull(transport);
        assertTrue(transport instanceof Car);
    }

    public void testCreateCarWithStringCaseInsensitive() {
        Transport transport = TransportFactory.createTransport("CAR");
        assertNotNull(transport);
        assertTrue(transport instanceof Car);
    }

    public void testCreateBikeWithString() {
        Transport transport = TransportFactory.createTransport("bike");
        assertNotNull(transport);
        assertTrue(transport instanceof Bike);
    }

    public void testCreateTruckWithString() {
        Transport transport = TransportFactory.createTransport("truck");
        assertNotNull(transport);
        assertTrue(transport instanceof Truck);
    }

    public void testInvalidStringThrowsException() {
        try {
            TransportFactory.createTransport("plane");
            fail("Expected IllegalArgumentException for invalid type");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Invalid transport type"));
        }
    }

    public void testNullStringThrowsException() {
        try {
            TransportFactory.createTransport((String) null);
            fail("Expected IllegalArgumentException for null string");
        } catch (IllegalArgumentException e) {
            assertEquals("Transport type cannot be null", e.getMessage());
        }
    }

    // --- Each transport produces a distinct instance ---

    public void testFactoryReturnsNewInstanceEachTime() {
        Transport t1 = TransportFactory.createTransport(TransportType.CAR);
        Transport t2 = TransportFactory.createTransport(TransportType.CAR);
        assertNotSame(t1, t2);
    }
}

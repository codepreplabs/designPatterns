package com.codeprep;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MobileDevice Tests")
class MobileDeviceTest {

    private MobileDevice mobileDevice;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        mobileDevice = new MobileDevice();
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    @DisplayName("Should update temperature correctly")
    void testUpdate() {
        mobileDevice.update(28.5f);
        mobileDevice.display();

        String output = outputStream.toString();
        assertTrue(output.contains("28.5"));
    }

    @Test
    @DisplayName("Should display correct temperature after multiple updates")
    void testMultipleUpdates() {
        mobileDevice.update(15.0f);
        mobileDevice.update(20.0f);
        mobileDevice.update(25.0f);
        mobileDevice.display();

        String output = outputStream.toString();
        assertTrue(output.contains("25.0"));
        assertFalse(output.contains("15.0"));
    }

    @Test
    @DisplayName("Should handle negative temperature")
    void testNegativeTemperature() {
        mobileDevice.update(-10.5f);
        mobileDevice.display();

        String output = outputStream.toString();
        assertTrue(output.contains("-10.5"));
    }

    @Test
    @DisplayName("Should display zero temperature")
    void testZeroTemperature() {
        mobileDevice.update(0.0f);
        mobileDevice.display();

        String output = outputStream.toString();
        assertTrue(output.contains("0.0"));
    }

    @Test
    @DisplayName("Should display output in correct format")
    void testDisplayFormat() {
        mobileDevice.update(35.5f);
        mobileDevice.display();

        String output = outputStream.toString().trim();
        assertEquals("Mobile Device temperature: 35.5", output);
    }

    @Test
    @DisplayName("Should handle high temperature values")
    void testHighTemperature() {
        mobileDevice.update(100.0f);
        mobileDevice.display();

        String output = outputStream.toString();
        assertTrue(output.contains("100.0"));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
}


package com.codeprep;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DisplayDevice Tests")
class DisplayDeviceTest {

    private DisplayDevice displayDevice;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        displayDevice = new DisplayDevice();
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    @DisplayName("Should update temperature correctly")
    void testUpdate() {
        displayDevice.update(25.5f);
        displayDevice.display();

        String output = outputStream.toString();
        assertTrue(output.contains("25.5"));
    }

    @Test
    @DisplayName("Should display correct temperature after multiple updates")
    void testMultipleUpdates() {
        displayDevice.update(20.0f);
        displayDevice.update(25.0f);
        displayDevice.update(30.0f);
        displayDevice.display();

        String output = outputStream.toString();
        assertTrue(output.contains("30.0"));
        assertFalse(output.contains("20.0"));
    }

    @Test
    @DisplayName("Should handle negative temperature")
    void testNegativeTemperature() {
        displayDevice.update(-5.5f);
        displayDevice.display();

        String output = outputStream.toString();
        assertTrue(output.contains("-5.5"));
    }

    @Test
    @DisplayName("Should display zero temperature")
    void testZeroTemperature() {
        displayDevice.update(0.0f);
        displayDevice.display();

        String output = outputStream.toString();
        assertTrue(output.contains("0.0"));
    }

    @Test
    @DisplayName("Should display output in correct format")
    void testDisplayFormat() {
        displayDevice.update(22.5f);
        displayDevice.display();

        String output = outputStream.toString().trim();
        assertEquals("Display Device temp: 22.5", output);
    }

    @Test
    void tearDown() {
        System.setOut(originalOut);
    }
}


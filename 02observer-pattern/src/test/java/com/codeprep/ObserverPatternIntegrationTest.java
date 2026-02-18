package com.codeprep;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Observer Pattern Integration Tests")
class ObserverPatternIntegrationTest {

    private WeatherStation weatherStation;
    private DisplayDevice displayDevice;
    private MobileDevice mobileDevice;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        weatherStation = new WeatherStation(20.0f);
        displayDevice = new DisplayDevice();
        mobileDevice = new MobileDevice();
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    @DisplayName("Should notify all devices when temperature changes")
    void testWeatherStationNotifiesAllDevices() {
        weatherStation.registerObserver(displayDevice);
        weatherStation.registerObserver(mobileDevice);

        weatherStation.setTemperature(25.5f);
        weatherStation.notifyObservers();

        displayDevice.display();
        mobileDevice.display();

        String output = outputStream.toString();
        assertTrue(output.contains("Display Device temp: 25.5"));
        assertTrue(output.contains("Mobile Device temperature: 25.5"));
    }

    @Test
    @DisplayName("Should handle device registration and removal")
    void testDeviceRegistrationAndRemoval() {
        weatherStation.registerObserver(displayDevice);
        weatherStation.registerObserver(mobileDevice);

        weatherStation.setTemperature(30.0f);
        weatherStation.notifyObservers();

        weatherStation.removeObserver(displayDevice);

        weatherStation.setTemperature(35.0f);
        weatherStation.notifyObservers();

        displayDevice.display();
        mobileDevice.display();

        String output = outputStream.toString();
        // DisplayDevice should show old temperature (30.0)
        // MobileDevice should show new temperature (35.0)
        assertTrue(output.contains("Display Device temp: 30.0"));
        assertTrue(output.contains("Mobile Device temperature: 35.0"));
    }

    @Test
    @DisplayName("Should handle multiple temperature updates across devices")
    void testMultipleTemperatureUpdates() {
        weatherStation.registerObserver(displayDevice);
        weatherStation.registerObserver(mobileDevice);

        // First update
        weatherStation.setTemperature(15.0f);
        weatherStation.notifyObservers();

        // Second update
        weatherStation.setTemperature(20.0f);
        weatherStation.notifyObservers();

        // Third update
        weatherStation.setTemperature(25.0f);
        weatherStation.notifyObservers();

        displayDevice.display();
        mobileDevice.display();

        String output = outputStream.toString();
        // Both devices should show the latest temperature
        assertTrue(output.contains("Display Device temp: 25.0"));
        assertTrue(output.contains("Mobile Device temperature: 25.0"));
    }

    @Test
    @DisplayName("Should work with single device")
    void testSingleDevice() {
        weatherStation.registerObserver(displayDevice);

        weatherStation.setTemperature(18.5f);
        weatherStation.notifyObservers();

        displayDevice.display();

        String output = outputStream.toString();
        assertTrue(output.contains("Display Device temp: 18.5"));
        assertFalse(output.contains("Mobile Device"));
    }

    @Test
    @DisplayName("Should handle extreme temperature values")
    void testExtremeTemperatures() {
        weatherStation.registerObserver(displayDevice);
        weatherStation.registerObserver(mobileDevice);

        // Very high temperature
        weatherStation.setTemperature(150.0f);
        weatherStation.notifyObservers();

        displayDevice.display();
        outputStream.reset();

        // Very low temperature
        weatherStation.setTemperature(-50.0f);
        weatherStation.notifyObservers();

        mobileDevice.display();

        String output = outputStream.toString();
        assertTrue(output.contains("-50.0"));
    }

    @Test
    @DisplayName("Should maintain independence between devices")
    void testDeviceIndependence() {
        weatherStation.registerObserver(displayDevice);
        weatherStation.setTemperature(25.0f);
        weatherStation.notifyObservers();

        // Remove display and add mobile
        weatherStation.removeObserver(displayDevice);
        weatherStation.registerObserver(mobileDevice);
        weatherStation.setTemperature(30.0f);
        weatherStation.notifyObservers();

        displayDevice.display();
        mobileDevice.display();

        String output = outputStream.toString();
        // DisplayDevice should have old temperature
        assertTrue(output.contains("Display Device temp: 25.0"));
        // MobileDevice should have new temperature
        assertTrue(output.contains("Mobile Device temperature: 30.0"));
    }

    @Test
    void tearDown() {
        System.setOut(originalOut);
    }
}


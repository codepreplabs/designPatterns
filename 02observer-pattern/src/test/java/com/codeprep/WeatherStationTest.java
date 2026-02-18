package com.codeprep;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("WeatherStation Tests")
class WeatherStationTest {

    private WeatherStation weatherStation;

    // Test Observer that tracks received temperature updates
    private static class TestObserver implements Observer {
        private final List<Float> receivedTemperatures = new ArrayList<>();

        @Override
        public void update(float temp) {
            receivedTemperatures.add(temp);
        }

        public int getUpdateCount() {
            return receivedTemperatures.size();
        }

        public List<Float> getReceivedTemperatures() {
            return receivedTemperatures;
        }

        public Float getLastTemperature() {
            return receivedTemperatures.isEmpty() ? null : receivedTemperatures.get(receivedTemperatures.size() - 1);
        }
    }

    @BeforeEach
    void setUp() {
        weatherStation = new WeatherStation(25.0f);
    }

    @Test
    @DisplayName("Should notify observer when registered")
    void testRegisterObserver() {
        TestObserver observer = new TestObserver();
        weatherStation.registerObserver(observer);
        weatherStation.notifyObservers();

        assertEquals(1, observer.getUpdateCount());
        assertEquals(25.0f, observer.getLastTemperature());
    }

    @Test
    @DisplayName("Should not notify observer after removal")
    void testRemoveObserver() {
        TestObserver observer = new TestObserver();
        weatherStation.registerObserver(observer);
        weatherStation.removeObserver(observer);
        weatherStation.notifyObservers();

        assertEquals(0, observer.getUpdateCount());
    }

    @Test
    @DisplayName("Should notify multiple observers")
    void testNotifyMultipleObservers() {
        TestObserver observer1 = new TestObserver();
        TestObserver observer2 = new TestObserver();

        weatherStation.registerObserver(observer1);
        weatherStation.registerObserver(observer2);
        weatherStation.notifyObservers();

        assertEquals(1, observer1.getUpdateCount());
        assertEquals(25.0f, observer1.getLastTemperature());
        assertEquals(1, observer2.getUpdateCount());
        assertEquals(25.0f, observer2.getLastTemperature());
    }

    @Test
    @DisplayName("Should notify with updated temperature")
    void testNotifyObserversWithUpdatedTemperature() {
        TestObserver observer = new TestObserver();
        weatherStation.registerObserver(observer);
        weatherStation.setTemperature(30.5f);
        weatherStation.notifyObservers();

        assertEquals(1, observer.getUpdateCount());
        assertEquals(30.5f, observer.getLastTemperature());
    }

    @Test
    @DisplayName("Should not throw exception when notifying with no observers")
    void testNotifyObserversWithNoRegisteredObservers() {
        assertDoesNotThrow(() -> weatherStation.notifyObservers());
    }

    @Test
    @DisplayName("Should allow registering same observer multiple times")
    void testRegisterSameObserverMultipleTimes() {
        TestObserver observer = new TestObserver();
        weatherStation.registerObserver(observer);
        weatherStation.registerObserver(observer);
        weatherStation.notifyObservers();

        // Observer will be notified twice if added twice
        assertEquals(2, observer.getUpdateCount());
    }

    @Test
    @DisplayName("Should update temperature correctly")
    void testSetTemperature() {
        TestObserver observer = new TestObserver();
        weatherStation.setTemperature(40.0f);
        weatherStation.registerObserver(observer);
        weatherStation.notifyObservers();

        assertEquals(40.0f, observer.getLastTemperature());
    }

    @Test
    @DisplayName("Should handle negative temperature")
    void testNegativeTemperature() {
        TestObserver observer = new TestObserver();
        weatherStation.setTemperature(-10.5f);
        weatherStation.registerObserver(observer);
        weatherStation.notifyObservers();

        assertEquals(-10.5f, observer.getLastTemperature());
    }

    @Test
    @DisplayName("Should only remove first occurrence of observer")
    void testRemoveObserverOnlyRemovesOne() {
        TestObserver observer = new TestObserver();
        weatherStation.registerObserver(observer);
        weatherStation.registerObserver(observer);
        weatherStation.removeObserver(observer);
        weatherStation.notifyObservers();

        // One instance should still remain
        assertEquals(1, observer.getUpdateCount());
    }

    @Test
    @DisplayName("Should handle multiple temperature updates")
    void testMultipleTemperatureUpdates() {
        TestObserver observer = new TestObserver();
        weatherStation.registerObserver(observer);

        weatherStation.setTemperature(20.0f);
        weatherStation.notifyObservers();

        weatherStation.setTemperature(25.0f);
        weatherStation.notifyObservers();

        weatherStation.setTemperature(30.0f);
        weatherStation.notifyObservers();

        assertEquals(3, observer.getUpdateCount());
        List<Float> temps = observer.getReceivedTemperatures();
        assertEquals(20.0f, temps.get(0));
        assertEquals(25.0f, temps.get(1));
        assertEquals(30.0f, temps.get(2));
    }
}



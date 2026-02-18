package com.codeprep;

import java.util.ArrayList;
import java.util.List;

public class WeatherStation implements Subject {

    private float temperature;
    private final List<Observer> observers = new ArrayList<>();

    public WeatherStation(float temperature) {
        this.temperature = temperature;
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(temperature);
        }
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
}

package com.codeprep;

public class DisplayDevice implements Observer {

    private float temperature;

    @Override
    public void update(float temp) {
        this.temperature = temp;
    }

    public void display() {
        System.out.println("Display Device temp: " + temperature);
    }
}

package com.codeprep;

public class MobileDevice implements Observer{

    private float temperature;

    @Override
    public void update(float temp) {
        this.temperature = temp;
    }

    public void display() {
        System.out.println("Mobile Device temperature: " + temperature);
    }
}

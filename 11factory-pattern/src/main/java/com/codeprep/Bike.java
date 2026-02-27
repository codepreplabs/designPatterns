package com.codeprep;

public class Bike implements Transport {

    private final String name = "Bike";

    @Override
    public void transport() {
        System.out.println(name + " is transporting");
    }

    @Override
    public String toString() {
        return name;
    }
}

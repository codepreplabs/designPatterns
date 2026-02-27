package com.codeprep;

public class Truck implements Transport {

    private final String name = "Truck";

    @Override
    public void transport() {
        System.out.println(name + " is transporting");
    }

    @Override
    public String toString() {
        return name;
    }
}

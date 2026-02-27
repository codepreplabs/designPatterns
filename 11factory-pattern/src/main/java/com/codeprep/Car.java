package com.codeprep;

public class Car implements Transport {

    private final String name = "Car";

    @Override
    public void transport() {
        System.out.println(name + " is transporting");
    }

    @Override
    public String toString() {
        return name;
    }
}

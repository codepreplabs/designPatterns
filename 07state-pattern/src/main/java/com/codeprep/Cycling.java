package com.codeprep;

public class Cycling implements TransportationMode
{
    @Override
    public double calculateETA() {
        System.out.println("Calculating ETA for cycling");
        return 5;
    }

    @Override
    public String getDirection() {
        return "Directions for cycling";
    }
}

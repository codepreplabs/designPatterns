package com.codeprep;

public class Walking implements TransportationMode{
    @Override
    public double calculateETA() {
        System.out.println("Calculating ETA for walking");
        return 10;
    }

    @Override
    public String getDirection() {
        return "Directions for walking";
    }
}

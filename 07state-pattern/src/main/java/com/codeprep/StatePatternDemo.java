package com.codeprep;

public class StatePatternDemo {

    static void main() {

        TransportationMode cycling = new Cycling();
        DirectionService directionService = new DirectionService(cycling);
        System.out.println(directionService.getDirection());
        System.out.println(directionService.getETA());
    }
}

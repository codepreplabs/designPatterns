package com.codeprep;

public class FactoryPatternDemo {

    public static void main(String[] args) {
        // Using enum (compile-time safe)
        Transport car = TransportFactory.createTransport(TransportType.CAR);
        car.transport();

        Transport bike = TransportFactory.createTransport(TransportType.BIKE);
        bike.transport();

        Transport truck = TransportFactory.createTransport(TransportType.TRUCK);
        truck.transport();

        // String overload still works for dynamic/runtime use
        Transport carFromString = TransportFactory.createTransport("car");
        carFromString.transport();
    }
}

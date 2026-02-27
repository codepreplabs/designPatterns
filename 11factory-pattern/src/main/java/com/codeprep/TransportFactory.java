package com.codeprep;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class TransportFactory {

    private static final Map<TransportType, Supplier<Transport>> registry = new HashMap<>();

    static {
        registry.put(TransportType.CAR, Car::new);
        registry.put(TransportType.BIKE, Bike::new);
        registry.put(TransportType.TRUCK, Truck::new);
    }

    public static Transport createTransport(TransportType type) {
        if (type == null) {
            throw new IllegalArgumentException("Transport type cannot be null");
        }
        Supplier<Transport> supplier = registry.get(type);
        if (supplier == null) {
            throw new IllegalArgumentException("No transport registered for type: " + type);
        }
        return supplier.get();
    }

    public static Transport createTransport(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Transport type cannot be null");
        }
        try {
            return createTransport(TransportType.valueOf(type.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid transport type: " + type);
        }
    }

    public static void register(TransportType type, Supplier<Transport> supplier) {
        if (type == null || supplier == null) {
            throw new IllegalArgumentException("Type and supplier must not be null");
        }
        registry.put(type, supplier);
    }
}

package com.codeprep;

public class DirectionService {

    private TransportationMode transportationMode;

    public DirectionService(TransportationMode transportationMode) {
        this.transportationMode = transportationMode;
    }

    public Double getETA(){
        return transportationMode.calculateETA();
    }

    public String getDirection(){
        return transportationMode.getDirection();
    }

    public void setTransportationMode(TransportationMode transportationMode) {
        this.transportationMode = transportationMode;
    }
}

package com.codeprep;

public class ObserverDemo {

    static void main() {

        WeatherStation weatherStation = new WeatherStation(26.5f);

        DisplayDevice displayDevice = new DisplayDevice();
        weatherStation.registerObserver(displayDevice);
        weatherStation.notifyObservers();
        displayDevice.display();

        weatherStation.removeObserver(displayDevice);

        weatherStation.setTemperature(27);
        MobileDevice mobileDevice = new MobileDevice();
        weatherStation.registerObserver(mobileDevice);
        weatherStation.notifyObservers();
        mobileDevice.display();
    }
}

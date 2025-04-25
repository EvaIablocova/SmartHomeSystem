package org.example.smarthomesystem.state;

import org.example.smarthomesystem.devices.Device;

public class AtHomeState implements HomeState {
    @Override
    public void apply(Device light, Device thermostat) {
        light.turnOn();
        thermostat.setValue(24); // Комфортная температура
        System.out.println("At Home state applied");
    }
}

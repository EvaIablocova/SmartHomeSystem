package org.example.smarthomesystem.devices.adapter;

import org.example.smarthomesystem.devices.Device;

public class ThermostatAdapter implements Device {
    private final ThirdPartyThermostat thirdPartyThermostat;

    public ThermostatAdapter(ThirdPartyThermostat thirdPartyThermostat) {
        this.thirdPartyThermostat = thirdPartyThermostat;
    }

    @Override
    public void turnOn() {
        System.out.println("Thermostat turned on via adapter");
    }

    @Override
    public void turnOff() {
        System.out.println("Thermostat turned off via adapter");
    }

    @Override
    public void setValue(int value) {
        thirdPartyThermostat.setTemp(value);
    }
}

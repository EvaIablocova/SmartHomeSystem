package org.example.smarthomesystem.decorator;

import org.example.smarthomesystem.devices.Device;

public class LoggingDeviceDecorator implements Device {
    private final Device device;

    public LoggingDeviceDecorator(Device device) {
        this.device = device;
    }

    @Override
    public void turnOn() {
        System.out.println("Log: Turning on device");
        device.turnOn();
    }

    @Override
    public void turnOff() {
        System.out.println("Log: Turning off device");
        device.turnOff();
    }

    @Override
    public void setValue(int value) {
        System.out.println("Log: Setting value to " + value);
        device.setValue(value);
    }
}

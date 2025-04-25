package org.example.smarthomesystem.devices.proxy;

import org.example.smarthomesystem.devices.Device;

public class DeviceProxy implements Device {
    private final Device device;
    private final String userRole;

    public DeviceProxy(Device device, String userRole) {
        this.device = device;
        this.userRole = userRole;
    }

    @Override
    public void turnOn() {
        if (userRole.equals("admin")) {
            device.turnOn();
        } else {
            System.out.println("Access denied: Admin role required");
        }
    }

    @Override
    public void turnOff() {
        if (userRole.equals("admin")) {
            device.turnOff();
        } else {
            System.out.println("Access denied: Admin role required");
        }
    }

    @Override
    public void setValue(int value) {
        if (userRole.equals("admin")) {
            device.setValue(value);
        } else {
            System.out.println("Access denied: Admin role required");
        }
    }
}

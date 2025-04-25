package org.example.smarthomesystem.devices.composite;

import java.util.ArrayList;
import java.util.List;

public class DeviceGroup implements DeviceComponent {
    private final String name;
    private final List<DeviceComponent> components = new ArrayList<>();

    public DeviceGroup(String name) {
        this.name = name;
    }

    public void add(DeviceComponent component) {
        components.add(component);
    }

    @Override
    public void turnOn() {
        System.out.println("Turning on group: " + name);
        components.forEach(DeviceComponent::turnOn);
    }

    @Override
    public void turnOff() {
        System.out.println("Turning off group: " + name);
        components.forEach(DeviceComponent::turnOff);
    }
}

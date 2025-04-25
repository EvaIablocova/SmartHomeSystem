package org.example.smarthomesystem.strategy;

import org.example.smarthomesystem.devices.Device;

public class EcoMode implements EnergyModeStrategy {
    @Override
    public void applyMode(Device device) {
        device.setValue(10); // Низкий уровень энергопотребления
        System.out.println("Eco mode applied");
    }
}

package org.example.smarthomesystem.strategy;

import org.example.smarthomesystem.devices.Device;

public interface EnergyModeStrategy {
    void applyMode(Device device);
}

package org.example.smarthomesystem.state;

import org.example.smarthomesystem.devices.Device;

public interface HomeState {
    void apply(Device light, Device thermostat);
}

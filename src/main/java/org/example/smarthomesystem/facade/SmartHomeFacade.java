package org.example.smarthomesystem.facade;

import lombok.Getter;
import org.example.smarthomesystem.devices.Device;
import org.example.smarthomesystem.entity.DeviceState;
import org.example.smarthomesystem.mediator.SmartHomeMediator;
import org.example.smarthomesystem.repository.DeviceStateRepository;
import org.springframework.stereotype.Component;

@Component
@Getter
public class SmartHomeFacade {
    private final Device light;
    private final Device thermostat;
    private final Device securitySystem;
    private final SmartHomeMediator mediator;
    private final DeviceStateRepository repository;

    public SmartHomeFacade(Device light, Device thermostat, Device securitySystem,
                           SmartHomeMediator mediator, DeviceStateRepository repository) {
        this.light = light;
        this.thermostat = thermostat;
        this.securitySystem = securitySystem;
        this.mediator = mediator;
        this.repository = repository;
    }

    public void eveningMode() {
        mediator.executeScenario("Evening Mode");
        light.setValue(50);
        thermostat.setValue(22);
        securitySystem.turnOn();
        saveState("Light", true, 50);
        saveState("Thermostat", true, 22);
        saveState("SecuritySystem", true, 0);
    }

    public void morningMode() {
        mediator.executeScenario("Morning Mode");
        light.setValue(80);
        thermostat.setValue(24);
        securitySystem.turnOff();
        saveState("Light", true, 80);
        saveState("Thermostat", true, 24);
        saveState("SecuritySystem", false, 0);
    }

    private void saveState(String deviceName, boolean isOn, int value) {
        DeviceState state = new DeviceState();
        state.setDeviceName(deviceName);
        state.setOn(isOn);
        state.setValue(value);
        repository.save(state);
    }
}
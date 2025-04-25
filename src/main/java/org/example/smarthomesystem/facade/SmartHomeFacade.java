package org.example.smarthomesystem.facade;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.example.smarthomesystem.devices.Device;
import org.example.smarthomesystem.entity.DeviceState;
import org.example.smarthomesystem.mediator.SmartHomeMediator;
import org.example.smarthomesystem.repository.DeviceStateRepository;
import org.springframework.stereotype.Component;

@Getter
@Component
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

    @PostConstruct
    public void init() {
        // Инициализация устройств в базе данных, если их там нет
        if (!repository.existsById("Light")) {
            saveState("Light", false, 0);
        }
        if (!repository.existsById("Thermostat")) {
            saveState("Thermostat", false, 20);
        }
        if (!repository.existsById("SecuritySystem")) {
            saveState("SecuritySystem", false, 0);
        }
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
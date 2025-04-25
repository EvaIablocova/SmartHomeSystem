package org.example.smarthomesystem.devices;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Thermostat implements Device {
    private boolean isOn = false;
    private int temperature = 20; // Температура по умолчанию

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("Thermostat turned on, set to " + temperature + "°C");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("Thermostat turned off");
    }

    @Override
    public void setValue(int value) {
        if (value >= 10 && value <= 30) { // Ограничение температуры
            temperature = value;
            if (isOn) {
                System.out.println("Thermostat temperature set to " + temperature + "°C");
            }
        } else {
            System.out.println("Invalid temperature: " + value + "°C. Must be between 10 and 30.");
        }
    }

    public boolean isOn() {
        return isOn;
    }

}
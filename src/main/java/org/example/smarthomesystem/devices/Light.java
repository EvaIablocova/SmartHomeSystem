package org.example.smarthomesystem.devices;

import lombok.Getter;

@Getter
public class Light implements Device {
    private boolean isOn = false;
    private int brightness = 0;

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("Light turned on");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("Light turned off");
    }

    @Override
    public void setValue(int value) {
        brightness = value;
        System.out.println("Light brightness set to " + value + "%");
    }
}
package org.example.smarthomesystem.devices;

import org.example.smarthomesystem.observer.DeviceObserver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SecuritySystem implements Device {
    private boolean isOn = false;
    private final List<DeviceObserver> observers = new ArrayList<>();

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("Security system activated");
        notifyObservers("security_activated");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("Security system deactivated");
        notifyObservers("security_deactivated");
    }

    @Override
    public void setValue(int value) {
        // Для системы безопасности значение может быть заглушкой или конфигурацией
        System.out.println("Security system configuration updated: " + value);
    }

    // Метод для имитации события (например, срабатывание датчика)
    public void triggerMotionDetected() {
        if (isOn) {
            notifyObservers("motion_detected");
        }
    }

    public void addObserver(DeviceObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(String event) {
        observers.forEach(observer -> observer.update(event));
    }

    public boolean isOn() {
        return isOn;
    }
}

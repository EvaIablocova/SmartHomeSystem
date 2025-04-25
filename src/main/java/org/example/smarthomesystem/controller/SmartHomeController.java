package org.example.smarthomesystem.controller;

import org.example.smarthomesystem.devices.Device;
import org.example.smarthomesystem.facade.SmartHomeFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.example.smarthomesystem.devices.SecuritySystem;
import org.example.smarthomesystem.repository.DeviceStateRepository;
import org.example.smarthomesystem.entity.DeviceState;

@Controller
public class SmartHomeController {

    private final SmartHomeFacade facade;
    private final DeviceStateRepository repository;
    private final SecuritySystem securitySystem;

    @Autowired
    public SmartHomeController(SmartHomeFacade facade, DeviceStateRepository repository, SecuritySystem securitySystem) {
        this.facade = facade;
        this.repository = repository;
        this.securitySystem = securitySystem;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Welcome to Smart Home System⛺️");
        return "index";
    }

    @GetMapping("/devices")
    public String devices(Model model) {
        model.addAttribute("deviceStates", repository.findAll());
        return "devices";
    }

    @PostMapping("/evening-mode")
    public String eveningMode(Model model) {
        facade.eveningMode();
        model.addAttribute("message", "Evening Mode activated!✨");
        return "index";
    }

    @PostMapping("/morning-mode")
    public String morningMode(Model model) {
        facade.morningMode();
        model.addAttribute("message", "Morning Mode activated!🌼");
        return "index";
    }

    @PostMapping("/control-device")
    public String controlDevice(
            @RequestParam String deviceName,
            @RequestParam String action,
            @RequestParam(required = false) Integer value,
            Model model) {
        try {
            Device device = switch (deviceName) {
                case "Light" -> facade.getLight();
                case "Thermostat" -> facade.getThermostat();
                case "SecuritySystem" -> facade.getSecuritySystem();
                default -> null; // Для новых устройств, добавленных через форму
            };

//            if (device == null) {
//                model.addAttribute("message", "Device " + deviceName + " is not controllable yet!");
//                return "devices";
//            }

            if ("turnOn".equals(action)) {
                device.turnOn();
                updateDeviceState(deviceName, true, getDeviceValue(device));
                model.addAttribute("message", deviceName + " turned on");
            } else if ("turnOff".equals(action)) {
                device.turnOff();
                updateDeviceState(deviceName, false, getDeviceValue(device));
                model.addAttribute("message", deviceName + " turned off");
            } else if ("setValue".equals(action)) {
                if (value == null) {
                    model.addAttribute("message", "Please provide a value for " + deviceName);
                    return "devices";
                }
                if (deviceName.equals("Light") && (value < 0 || value > 100)) {
                    model.addAttribute("message", "Brightness must be between 0 and 100!");
                    return "devices";
                } else if (deviceName.equals("Thermostat") && (value < 10 || value > 30)) {
                    model.addAttribute("message", "Temperature must be between 10 and 30!");
                    return "devices";
                }
                device.setValue(value);
                updateDeviceState(deviceName, isDeviceOn(device), value);
                model.addAttribute("message", "Value set for " + deviceName + ": " + value);
            }
        } catch (Exception e) {
            model.addAttribute("message", "Error performing action on " + deviceName + ": " + e.getMessage());
        }
        return "devices";
    }

    @PostMapping("/delete-device")
    public String deleteDevice(@RequestParam String deviceName, Model model) {
        try {
            if (repository.existsById(deviceName)) {
                repository.deleteById(deviceName);
                model.addAttribute("message", "Device " + deviceName + " deleted!");
            } else {
                model.addAttribute("message", "Device " + deviceName + " not found!");
            }
        } catch (Exception e) {
            model.addAttribute("message", "Error deleting " + deviceName + ": " + e.getMessage());
        }
        return "devices";
    }

    @PostMapping("/trigger-motion")
    public String triggerMotion(Model model) {
        securitySystem.triggerMotionDetected();
        model.addAttribute("message", "Motion detection triggered!");
        return "index";
    }

    private boolean isDeviceOn(Device device) {
        if (device instanceof org.example.smarthomesystem.devices.Light light) {
            return light.isOn();
        } else if (device instanceof org.example.smarthomesystem.devices.Thermostat thermostat) {
            return thermostat.isOn();
        } else if (device instanceof org.example.smarthomesystem.devices.SecuritySystem securitySystem) {
            return securitySystem.isOn();
        }
        return false;
    }

    private int getDeviceValue(Device device) {
        if (device instanceof org.example.smarthomesystem.devices.Light light) {
            return light.getBrightness();
        } else if (device instanceof org.example.smarthomesystem.devices.Thermostat thermostat) {
            return thermostat.getTemperature();
        }
        return 0;
    }

    private void updateDeviceState(String deviceName, boolean isOn, int value) {
        DeviceState state = repository.findById(deviceName).orElse(new DeviceState());
        state.setDeviceName(deviceName);
        state.setOn(isOn);
        state.setValue(value);
        repository.save(state);
    }
}
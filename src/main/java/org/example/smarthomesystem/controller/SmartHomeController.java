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
        model.addAttribute("message", "Welcome to Smart Home System");
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
        model.addAttribute("message", "Evening Mode activated!");
        return "index";
    }

    @PostMapping("/morning-mode")
    public String morningMode(Model model) {
        facade.morningMode();
        model.addAttribute("message", "Morning Mode activated!");
        return "index";
    }

    @PostMapping("/control-device")
    public String controlDevice(
            @RequestParam String deviceName,
            @RequestParam String action,
            @RequestParam(required = false) Integer value,
            Model model) {
        Device device = switch (deviceName) {
            case "Light" -> facade.getLight();
            case "Thermostat" -> facade.getThermostat();
            case "SecuritySystem" -> facade.getSecuritySystem();
            default -> throw new IllegalArgumentException("Unknown device: " + deviceName);
        };
        if ("turnOn".equals(action)) {
            device.turnOn();
        } else if ("turnOff".equals(action)) {
            device.turnOff();
        } else if ("setValue".equals(action) && value != null) {
            device.setValue(value);
        }
        model.addAttribute("message", "Action performed on " + deviceName);
        return "devices";
    }

    @PostMapping("/trigger-motion")
    public String triggerMotion(Model model) {
        securitySystem.triggerMotionDetected();
        model.addAttribute("message", "Motion detection triggered!");
        return "index";
    }
}
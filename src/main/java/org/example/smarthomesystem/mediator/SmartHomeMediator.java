package org.example.smarthomesystem.mediator;

import org.example.smarthomesystem.commands.Command;
import org.example.smarthomesystem.devices.Device;

import java.util.ArrayList;
import java.util.List;

public class SmartHomeMediator {
    private final List<Device> devices = new ArrayList<>();
    private final List<Command> commands = new ArrayList<>();

    public void registerDevice(Device device) {
        devices.add(device);
    }

    public void registerCommand(Command command) {
        commands.add(command);
    }

    public void executeScenario(String scenario) {
        System.out.println("Executing scenario: " + scenario);
        commands.forEach(Command::execute);
    }
}

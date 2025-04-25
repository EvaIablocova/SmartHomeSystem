package org.example.smarthomesystem.config;

import org.example.smarthomesystem.devices.Device;
import org.example.smarthomesystem.devices.Light;
import org.example.smarthomesystem.devices.adapter.ThermostatAdapter;
import org.example.smarthomesystem.devices.adapter.ThirdPartyThermostat;
import org.example.smarthomesystem.decorator.LoggingDeviceDecorator;
import org.example.smarthomesystem.devices.proxy.DeviceProxy;
import org.example.smarthomesystem.facade.SmartHomeFacade;
import org.example.smarthomesystem.mediator.SmartHomeMediator;
import org.example.smarthomesystem.repository.DeviceStateRepository;
import org.example.smarthomesystem.state.AtHomeState;
import org.example.smarthomesystem.state.HomeState;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.example.smarthomesystem.devices.SecuritySystem;
import org.example.smarthomesystem.devices.Thermostat;
import org.example.smarthomesystem.observer.SecurityAlertObserver;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Configuration
public class AppConfig {

    @Bean
    public Device light() {
        return new LoggingDeviceDecorator(new DeviceProxy(new Light(), "admin"));
    }

//    @Bean
//    public Device thermostat() {
//        return new LoggingDeviceDecorator(new DeviceProxy(new ThermostatAdapter(new ThirdPartyThermostat()), "admin"));
//    }

//    @Bean
//    public Device securitySystem(SimpMessagingTemplate messagingTemplate) {
//        SecuritySystem securitySystem = new SecuritySystem();
//        securitySystem.addObserver(new SecurityAlertObserver(messagingTemplate));
//        return new LoggingDeviceDecorator(new DeviceProxy(securitySystem, "admin"));
//    }

    @Bean
    public SmartHomeMediator mediator(Device light, Device thermostat, Device securitySystem) {
        SmartHomeMediator mediator = new SmartHomeMediator();
        mediator.registerDevice(light);
        mediator.registerDevice(thermostat);
        mediator.registerDevice(securitySystem);
        return mediator;
    }

//    @Bean
//    public SmartHomeFacade facade(Device light, Device thermostat, Device securitySystem,
//                                  SmartHomeMediator mediator, DeviceStateRepository repository) {
//        return new SmartHomeFacade(light, thermostat, securitySystem, mediator, repository);
//    }
}
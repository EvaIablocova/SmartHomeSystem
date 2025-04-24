//package org.example.smarthomesystem.facade;
//
////import com.example.smarthome.devices.Device;
////import com.example.smarthome.mediator.SmartHomeMediator;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SmartHomeFacade {
//    private final Device light;
//    private final Device thermostat;
//    private final Device securitySystem;
//    private final SmartHomeMediator mediator;
//
//    public SmartHomeFacade(Device light, Device thermostat, Device securitySystem, SmartHomeMediator mediator) {
//        this.light = light;
//        this.thermostat = thermostat;
//        this.securitySystem = securitySystem;
//        this.mediator = mediator;
//    }
//
//    public void eveningMode() {
//        mediator.executeScenario("Evening Mode");
//        light.setValue(50); // 50% яркости
//        thermostat.setValue(22); // 22°C
//        securitySystem.turnOn();
//    }
//
//    // Геттеры для доступа к устройствам из контроллера
//    public Device getLight() {
//        return light;
//    }
//
//    public Device getThermostat() {
//        return thermostat;
//    }
//
//    public Device getSecuritySystem() {
//        return securitySystem;
//    }
//}

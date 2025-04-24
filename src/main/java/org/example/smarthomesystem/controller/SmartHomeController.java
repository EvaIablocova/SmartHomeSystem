package org.example.smarthomesystem.controller;

//import com.example.smarthome.devices.Device;
//import com.example.smarthome.facade.SmartHomeFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SmartHomeController {

//    private final SmartHomeFacade facade;

//    @Autowired
//    public SmartHomeController(SmartHomeFacade facade) {
//        this.facade = facade;
//    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Welcome to Smart Home System");
        return "index";
    }

    @GetMapping("/devices")
    public String devices(Model model) {
        // Модель для отображения устройств (можно добавить список устройств)
        model.addAttribute("devices", new String[]{"Light", "Thermostat", "Security System"});
        return "devices";
    }
//
//    @PostMapping("/evening-mode")
//    public String eveningMode(Model model) {
//        facade.eveningMode();
//        model.addAttribute("message", "Evening Mode activated!");
//        return "index";
//    }
//
//    @PostMapping("/control-device")
//    public String controlDevice(
//            @RequestParam String deviceName,
//            @RequestParam String action,
//            @RequestParam(required = false) Integer value,
//            Model model) {
//        // Пример управления устройством
//        if ("Light".equals(deviceName)) {
//            Device light = facade.getLight(); // Доступ через фасад
//            if ("turnOn".equals(action)) {
//                light.turnOn();
//            } else if ("turnOff".equals(action)) {
//                light.turnOff();
//            } else if ("setValue".equals(action) && value != null) {
//                light.setValue(value);
//            }
//        }
//        model.addAttribute("message", "Action performed on " + deviceName);
//        return "devices";
//    }
}
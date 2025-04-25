package org.example.smarthomesystem.observer;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class SecurityAlertObserver implements DeviceObserver {
    private final SimpMessagingTemplate messagingTemplate;

    public SecurityAlertObserver(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void update(String event) {
        String message = switch (event) {
            case "motion_detected" -> "Security Alert: Motion detected!";
            case "security_activated" -> "Security system activated.";
            case "security_deactivated" -> "Security system deactivated.";
            default -> "Unknown event: " + event;
        };
        System.out.println(message);
        messagingTemplate.convertAndSend("/topic/notifications", message);
    }
}
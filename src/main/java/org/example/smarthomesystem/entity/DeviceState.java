package org.example.smarthomesystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
//import org.example.ordermanagementsystem4.APresentationLayer.DTOs.OrderDTO;

@Data
@Entity
@Table(name = "device_state")
public class DeviceState {
    @Id
    private String deviceName;
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;


    private boolean isOn;
    private int value;

    public DeviceState() {}

//    public DeviceState(String deviceName, boolean isOn, int value) {
    public DeviceState(boolean isOn, int value) {
        this.isOn = isOn;
        this.value = value;
    }

//    public DeviceState(DeviceStateDTO DeviceStateDTO) {
//
//    }
//

}
package org.example.smarthomesystem.repository;

import org.example.smarthomesystem.entity.DeviceState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceStateRepository extends JpaRepository<DeviceState, String> {
}

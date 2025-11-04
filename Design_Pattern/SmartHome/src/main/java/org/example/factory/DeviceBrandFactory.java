package org.example.factory;

import org.example.device.SmartDevice;

public interface DeviceBrandFactory {
    SmartDevice createLight(String id, String name);
    SmartDevice createThermostat(String id, String name);
    SmartDevice createDoorLock(String id, String name);
    SmartDevice createCamera(String id, String name);
}

package org.example.factory;

import org.example.device.SmartDevice;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DeviceFactory {
    private final Map<String, DeviceBrandFactory> brandMap = new HashMap<>();
    public DeviceFactory() {
        brandMap.put("BrandA", new BrandAFactory());
        brandMap.put("BrandB", new BrandBFactory());
    }
    public Optional<DeviceBrandFactory> getBrandFactory(String brand) {
        return Optional.ofNullable(brandMap.get(brand));
    }

    // convenience: create by brand and type
    public SmartDevice create(String brand, String type, String id, String name) {
        DeviceBrandFactory factory = brandMap.get(brand);
        if (factory == null) throw new IllegalArgumentException("Unknown brand: " + brand);
        switch (type.toLowerCase()) {
            case "light": return factory.createLight(id, name);
            case "thermostat": return factory.createThermostat(id, name);
            case "doorlock": return factory.createDoorLock(id, name);
            case "camera": return factory.createCamera(id, name);
            default: throw new IllegalArgumentException("Unknown device type: " + type);
        }
    }
}

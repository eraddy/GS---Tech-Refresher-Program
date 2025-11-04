package org.example.controller;

import org.example.command.Command;
import org.example.device.SmartDevice;
import org.example.factory.DeviceFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class SmartHomeController {
    private static final SmartHomeController INSTANCE = new SmartHomeController();
    private final Map<String, SmartDevice> deviceRegistry = new ConcurrentHashMap<>();
    private final DeviceFactory deviceFactory = new DeviceFactory();

    private SmartHomeController() {}

    public static SmartHomeController getInstance() { return INSTANCE; }

    public void registerDevice(SmartDevice device) {
        deviceRegistry.put(device.getId(), device);
        System.out.println("Registered device: " + device.getName() + " (id=" + device.getId() + ")");
    }

    public void unregisterDevice(String id) {
        SmartDevice removed = deviceRegistry.remove(id);
        if (removed != null) System.out.println("Unregistered device: " + removed.getName());
    }

    public Optional<SmartDevice> getDevice(String id) {
        return Optional.ofNullable(deviceRegistry.get(id));
    }

    public Collection<SmartDevice> listDevices() {
        return Collections.unmodifiableCollection(deviceRegistry.values());
    }

    public void executeCommand(Command command) {
        command.execute();
    }

    public DeviceFactory getDeviceFactory() { return deviceFactory; }

    // for small convenience: create and register device from brand/type
    public SmartDevice createAndRegister(String brand, String type, String id, String name) {
        SmartDevice d = deviceFactory.create(brand, type, id, name);
        registerDevice(d);
        return d;
    }
}

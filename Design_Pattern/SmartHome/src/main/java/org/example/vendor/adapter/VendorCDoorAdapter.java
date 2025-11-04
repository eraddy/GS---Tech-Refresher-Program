package org.example.vendor.adapter;

import org.example.device.SmartDevice;
import org.example.vendor.VendorCDoorAPI;

public class VendorCDoorAdapter implements SmartDevice {
    private final String id;
    private final String name;
    private final VendorCDoorAPI api;
    private boolean locked;

    public VendorCDoorAdapter(String id, String name, VendorCDoorAPI api) {
        this.id = id; this.name = name; this.api = api;
    }

    @Override public String getId() { return id; }
    @Override public String getName() { return name; }
    @Override public void turnOn() { api.setSecure(true); locked = true; }
    @Override public void turnOff() { api.setSecure(false); locked = false; }
    @Override public boolean isOn() { return locked; }

    public void lock() { api.setSecure(true); locked = true; }
    public void unlock() { api.setSecure(false); locked = false; }
}

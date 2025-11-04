package org.example.vendor.adapter;

import org.example.device.SmartDevice;
import org.example.vendor.VendorALightAPI;

public class VendorALightAdapter implements SmartDevice {
    private final String id;
    private final String name;
    private final VendorALightAPI api;
    private boolean on;

    public VendorALightAdapter(String id, String name, VendorALightAPI api) {
        this.id = id;
        this.name = name;
        this.api = api;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void turnOn() {
        api.power(true);
        on = true;
    }

    @Override
    public void turnOff() {
        api.power(false);
        on = false;
    }

    @Override
    public boolean isOn() {
        return on;
    }

    public void setBrightness(int level) {
        api.bright(level);
    }
}

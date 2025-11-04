package org.example.vendor.adapter;

import org.example.device.SmartDevice;
import org.example.vendor.VendorBThermostatAPI;

public class VendorBThermostatAdapter implements SmartDevice {
    private final String id;
    private final String name;
    private final VendorBThermostatAPI api;
    private boolean on;

    public VendorBThermostatAdapter(String id, String name, VendorBThermostatAPI api) {
        this.id = id; this.name = name; this.api = api;
    }

    @Override public String getId() { return id; }
    @Override public String getName() { return name; }
    @Override public void turnOn() { api.enable(); on = true; }
    @Override public void turnOff() { api.disable(); on = false; }
    @Override public boolean isOn() { return on; }

    public void setTemperature(double t) { api.changeTemp(t);
    }
}

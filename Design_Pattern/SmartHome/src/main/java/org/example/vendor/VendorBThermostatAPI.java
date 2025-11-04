package org.example.vendor;

public class VendorBThermostatAPI {
    private boolean active;
    public void enable() { active = true; System.out.println("[VendorB Thermostat] enabled"); }
    public void disable() { active = false; System.out.println("[VendorB Thermostat] disabled"); }
    public void changeTemp(double t) { System.out.println("[VendorB Thermostat] temp=" + t); }
}

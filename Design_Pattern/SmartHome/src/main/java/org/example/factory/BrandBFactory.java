package org.example.factory;

import org.example.device.DoorLock;
import org.example.device.Light;
import org.example.device.SecurityCamera;
import org.example.device.SmartDevice;
import org.example.vendor.VendorBThermostatAPI;
import org.example.vendor.adapter.VendorBThermostatAdapter;

public class BrandBFactory implements DeviceBrandFactory{
    @Override public SmartDevice createLight(String id, String name) {
        return new Light(id, name + " (BrandB)");
    }
    @Override public SmartDevice createThermostat(String id, String name) {
        VendorBThermostatAPI api = new VendorBThermostatAPI();
        return new VendorBThermostatAdapter(id, name + " (BrandB)", api);
    }
    @Override public SmartDevice createDoorLock(String id, String name) {
        return new DoorLock(id, name + " (BrandB)");
    }
    @Override public SmartDevice createCamera(String id, String name) {
        return new SecurityCamera(id, name + " (BrandB)");
    }
}

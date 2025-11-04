package org.example.factory;

import org.example.device.SecurityCamera;
import org.example.device.SmartDevice;
import org.example.device.Thermostat;
import org.example.vendor.VendorALightAPI;
import org.example.vendor.VendorCDoorAPI;
import org.example.vendor.adapter.VendorALightAdapter;
import org.example.vendor.adapter.VendorCDoorAdapter;

public class BrandAFactory implements DeviceBrandFactory{
    @Override public SmartDevice createLight(String id, String name) {
        VendorALightAPI api = new VendorALightAPI();
        return new VendorALightAdapter(id, name + " (BrandA)", api);
    }
    @Override public SmartDevice createThermostat(String id, String name) {
        // use default Thermostat
        return new Thermostat(id, name + " (BrandA)");
    }
    @Override public SmartDevice createDoorLock(String id, String name) {
        VendorCDoorAPI api = new VendorCDoorAPI();
        return new VendorCDoorAdapter(id, name + " (BrandA)", api);
    }
    @Override public SmartDevice createCamera(String id, String name) {
        return new SecurityCamera(id, name + " (BrandA)");
    }
}

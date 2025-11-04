package org.example.decorator;

import org.example.device.SmartDevice;

public abstract class DeviceDecorator implements SmartDevice {
    protected final SmartDevice inner;
    public DeviceDecorator(SmartDevice inner) { this.inner = inner; }
    @Override public String getId() { return inner.getId(); }
    @Override public String getName() { return inner.getName(); }
    @Override public boolean isOn() { return inner.isOn(); }
}

package org.example.device;

public interface SmartDevice {
    String getId();
    String getName();
    void turnOn();
    void turnOff();
    boolean isOn();
}

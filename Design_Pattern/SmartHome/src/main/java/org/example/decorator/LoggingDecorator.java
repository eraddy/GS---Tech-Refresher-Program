package org.example.decorator;

import org.example.device.SmartDevice;

public class LoggingDecorator extends DeviceDecorator{
    public LoggingDecorator(SmartDevice inner) { super(inner); }
    @Override public void turnOn() { System.out.println("[LOG] " + getName() + " -> turnOn"); inner.turnOn(); }
    @Override public void turnOff() { System.out.println("[LOG] " + getName() + " -> turnOff"); inner.turnOff(); }

}

package org.example.decorator;

import org.example.device.SmartDevice;

public class RemoteAccessDecorator extends DeviceDecorator{
    public RemoteAccessDecorator(SmartDevice inner) { super(inner); }
    @Override public void turnOn() { System.out.println("[Remote] remote turning ON " + getName()); inner.turnOn(); }
    @Override public void turnOff() { System.out.println("[Remote] remote turning OFF " + getName()); inner.turnOff(); }

}

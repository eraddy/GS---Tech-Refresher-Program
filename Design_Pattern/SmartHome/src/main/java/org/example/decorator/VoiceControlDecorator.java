package org.example.decorator;

import org.example.device.SmartDevice;

public class VoiceControlDecorator extends DeviceDecorator {
    public VoiceControlDecorator(SmartDevice inner) { super(inner); }
    @Override public void turnOn() { System.out.println("[Voice] invoking turnOn for " + getName()); inner.turnOn(); }
    @Override public void turnOff() { System.out.println("[Voice] invoking turnOff for " + getName()); inner.turnOff(); }

}

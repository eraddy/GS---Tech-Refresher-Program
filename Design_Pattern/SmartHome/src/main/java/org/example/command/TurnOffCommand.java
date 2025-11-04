package org.example.command;

import org.example.device.SmartDevice;

public class TurnOffCommand implements Command{
    private final SmartDevice device;
    public TurnOffCommand(SmartDevice d) { this.device = d; }
    @Override public void execute() { device.turnOff(); }
}

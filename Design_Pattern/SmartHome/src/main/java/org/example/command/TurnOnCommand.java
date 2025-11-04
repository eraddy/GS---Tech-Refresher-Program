package org.example.command;

import org.example.device.SmartDevice;

public class TurnOnCommand implements Command{
    private final SmartDevice device;
    public TurnOnCommand(SmartDevice d) { this.device = d; }
    @Override public void execute() { device.turnOn(); }
}

package org.example.command;

import org.example.device.SmartDevice;
import org.example.device.Thermostat;

public class SetTemperatureCommand implements Command{
    private final SmartDevice thermostat;
    private final double temp;
    public SetTemperatureCommand(SmartDevice thermostat, double temp) { this.thermostat = thermostat; this.temp = temp; }
    @Override public void execute() {
        // try reflection-like approach or type check for thermostat adapters
        if (thermostat instanceof Thermostat) {
            ((Thermostat)thermostat).setTemperature(temp);
        } else {
            try {
                // attempt typical adapter method naming
                thermostat.getClass().getMethod("setTemperature", double.class).invoke(thermostat, temp);
            } catch (Exception e) {
                System.out.println("Device " + thermostat.getName() + " does not support setTemperature()");
            }
        }
    }
}

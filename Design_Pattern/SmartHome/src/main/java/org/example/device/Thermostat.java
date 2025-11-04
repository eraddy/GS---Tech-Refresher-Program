package org.example.device;

public class Thermostat implements SmartDevice{
    protected final String id;
    protected final String name;
    protected boolean on = false;
    protected double temperature = 22.0;

    public Thermostat(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override public String getId() { return id; }
    @Override public String getName() { return name; }

    @Override public void turnOn() { on = true; System.out.println(name + " turned ON"); }
    @Override public void turnOff() { on = false; System.out.println(name + " turned OFF"); }
    @Override public boolean isOn() { return on; }

    public void setTemperature(double temp) {
        temperature = temp;
        System.out.println(name + " temperature set to " + temperature);
    }
}

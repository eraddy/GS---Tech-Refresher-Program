package org.example.device;

public class  Light implements SmartDevice{
    protected final String id;
    protected final String name;
    protected boolean on = false;
    protected int brightness = 100; // 0-100

    public Light(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override public String getId() { return id; }
    @Override public String getName() { return name; }

    @Override public void turnOn() { on = true; System.out.println(name + " turned ON"); }
    @Override public void turnOff() { on = false; System.out.println(name + " turned OFF"); }
    @Override public boolean isOn() { return on; }

    public void setBrightness(int value) {
        brightness = Math.max(0, Math.min(100, value));
        System.out.println(name + " brightness set to " + brightness);
    }
}

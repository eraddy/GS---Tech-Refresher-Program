package org.example.device;

public class SecurityCamera implements SmartDevice{
    protected final String id;
    protected final String name;
    protected boolean on = false;
    protected boolean recording = false;

    public SecurityCamera(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override public String getId() { return id; }
    @Override public String getName() { return name; }

    @Override public void turnOn() { on = true; recording = true; System.out.println(name + " camera ON & recording"); }
    @Override public void turnOff() { on = false; recording = false; System.out.println(name + " camera OFF"); }
    @Override public boolean isOn() { return on; }

    public void startRecording() { recording = true; System.out.println(name + " started recording"); }
    public void stopRecording() { recording = false; System.out.println(name + " stopped recording"); }

}

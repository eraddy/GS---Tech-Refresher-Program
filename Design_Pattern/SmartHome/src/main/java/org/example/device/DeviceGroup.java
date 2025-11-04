package org.example.device;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeviceGroup implements SmartDevice{
    private final String id;
    private final String name;
    private final List<SmartDevice> children = new ArrayList<>();

    public DeviceGroup(String id, String name) { this.id = id; this.name = name; }

    public void add(SmartDevice d) { children.add(d); }
    public void remove(SmartDevice d) { children.remove(d); }
    public List<SmartDevice> getChildren() { return Collections.unmodifiableList(children); }

    @Override public String getId() { return id; }
    @Override public String getName() { return name; }
    @Override public void turnOn() { System.out.println("Group " + name + ": turning ON all children"); children.forEach(SmartDevice::turnOn); }
    @Override public void turnOff() { System.out.println("Group " + name + ": turning OFF all children"); children.forEach(SmartDevice::turnOff); }
    @Override public boolean isOn() {
        return children.stream().anyMatch(SmartDevice::isOn);
    }
}

package org.example.device;

public class DoorLock implements SmartDevice{
    protected final String id;
    protected final String name;
    protected boolean locked = true;

    public DoorLock(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override public String getId() { return id; }
    @Override public String getName() { return name; }

    @Override public void turnOn() { lock(); }    // interpret turnOn as lock
    @Override public void turnOff() { unlock(); } // interpret turnOff as unlock
    @Override public boolean isOn() { return locked; }

    public void lock() { locked = true; System.out.println(name + " locked"); }
    public void unlock() { locked = false; System.out.println(name + " unlocked");
    }
}

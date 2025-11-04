package org.example.command;

import org.example.device.SmartDevice;

public class LockCommand implements Command{
    private final SmartDevice lock;
    public LockCommand(SmartDevice lock) { this.lock = lock; }
    @Override public void execute() {
        // try lock method, fallback to turnOn
        try {
            lock.getClass().getMethod("lock").invoke(lock);
        } catch (Exception e) {
            lock.turnOn();
        }
    }
}

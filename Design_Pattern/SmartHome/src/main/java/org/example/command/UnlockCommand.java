package org.example.command;

import org.example.device.SmartDevice;

public class UnlockCommand implements Command{
    private final SmartDevice lock;
    public UnlockCommand(SmartDevice lock) { this.lock = lock; }
    @Override public void execute() {
        try {
            lock.getClass().getMethod("unlock").invoke(lock);
        } catch (Exception e) {
            lock.turnOff();
        }
    }
}

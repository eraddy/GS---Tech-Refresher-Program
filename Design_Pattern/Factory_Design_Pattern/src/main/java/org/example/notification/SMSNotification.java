package org.example.notification;

import org.example.api.Notification;

public class SMSNotification implements Notification {
    @Override
    public void send() {
        System.out.println("SMS sent");
    }
}

package org.example.notification;

import org.example.api.Notification;

public class PushNotification implements Notification {
    @Override
    public void send() {
        System.out.println("Push notification sent");
    }
}

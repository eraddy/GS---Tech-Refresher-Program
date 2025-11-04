package org.example.notification;

import org.example.api.Notification;

public class EmailNotification implements Notification {

    @Override
    public void send() {
        System.out.println("Email sent");
    }
}

package org.example.application;

import org.example.api.Notification;
import org.example.factory.NotificationFactory;

public class Application {
    static void main() {
        try {
            Notification emailNotification = NotificationFactory.getNotification("email");
            emailNotification.send();

            Notification smsNotification = NotificationFactory.getNotification("sms");
            smsNotification.send();

            Notification pushNotification = NotificationFactory.getNotification("push");
            pushNotification.send();

            // This will intentionally cause an exception to test validation handling
            Notification exceptionCheck = NotificationFactory.getNotification("xyz");
            exceptionCheck.send();

        } catch (IllegalArgumentException e) {
            System.err.println("❌ Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("⚠️ Unexpected error occurred: " + e.getMessage());
        }
    }
}
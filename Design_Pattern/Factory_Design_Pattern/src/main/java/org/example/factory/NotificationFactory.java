package org.example.factory;

import org.example.api.Notification;
import org.example.entity.NotificationType;
import org.example.notification.EmailNotification;
import org.example.notification.PushNotification;
import org.example.notification.SMSNotification;

public class NotificationFactory {
    public static Notification getNotification(String category){
        if(category == null)
            throw new IllegalArgumentException("Null input not allowed");
        NotificationType type = NotificationType.getType(category);
        if(type == NotificationType.EMAIL)
            return new EmailNotification();
        else if (type == NotificationType.SMS)
            return new SMSNotification();
        else
            return new PushNotification();
    }
}

package org.example.factory;

import org.example.api.Notification;
import org.example.entity.NotificationType;
import org.example.exception.InvalidNotificationType;
import org.example.notification.EmailNotification;
import org.example.notification.PushNotification;
import org.example.notification.SMSNotification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationFactoryTest {
    @Test
    void testGetEmailNotification() {
        Notification notification = NotificationFactory.getNotification("email");
        assertNotNull(notification, "Factory should return a non-null Notification");
        assertTrue(notification instanceof EmailNotification, "Expected EmailNotification instance");
    }

    @Test
    void testGetSMSNotification() {
        Notification notification = NotificationFactory.getNotification("sms");
        assertNotNull(notification);
        assertTrue(notification instanceof SMSNotification, "Expected SMSNotification instance");
    }

    @Test
    void testGetPushNotification() {
        Notification notification = NotificationFactory.getNotification("push");
        assertNotNull(notification);
        assertTrue(notification instanceof PushNotification, "Expected PushNotification instance");
    }

    @Test
    void testCaseInsensitiveHandling() {
        // Assuming NotificationType.getType handles case-insensitivity (e.g., toUpperCase)
        Notification notification = NotificationFactory.getNotification("EmAiL");
        assertTrue(notification instanceof EmailNotification);
    }

    @Test
    void testInvalidNotificationType() {
        // Assuming NotificationType.getType throws IllegalArgumentException for invalid type
        assertThrows(InvalidNotificationType.class, () ->
                NotificationFactory.getNotification("unknown"));
    }

    @Test
    void testNullInputShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () ->
                NotificationFactory.getNotification(null));
    }

    @Test
    void testWhitespaceInputShouldThrowException() {
        assertThrows(InvalidNotificationType.class, () ->
                NotificationFactory.getNotification("  "));
    }

    @Test
    void testEnumMapping() {
        // Just to ensure that NotificationType enum works properly
        NotificationType type = NotificationType.getType("sms");
        assertEquals(NotificationType.SMS, type);
    }
}
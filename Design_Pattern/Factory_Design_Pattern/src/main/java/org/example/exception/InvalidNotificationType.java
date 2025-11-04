package org.example.exception;

public class InvalidNotificationType extends RuntimeException {
    public InvalidNotificationType(String message) {
        super(message);
    }
}

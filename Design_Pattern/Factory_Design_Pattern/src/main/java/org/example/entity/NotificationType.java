package org.example.entity;

import org.example.exception.InvalidNotificationType;

public enum NotificationType {
    EMAIL,
    SMS,
    PUSH;

    public static NotificationType getType(String type)
    {
        if(type.equalsIgnoreCase("email"))
            return EMAIL;
        else if (type.equalsIgnoreCase("sms"))
            return SMS;
        else if(type.equalsIgnoreCase("push"))
            return PUSH;
        else
            throw new InvalidNotificationType("No notification of : " + type + " exists");
    }
}

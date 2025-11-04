package com.designpattern.exception;

import com.designpattern.dto.User;

public class InsufficientRightsException extends RuntimeException {
    public InsufficientRightsException(User user, String path) {
        super("User " + user.getUsername() + " does not have access to " + path);
    }
}
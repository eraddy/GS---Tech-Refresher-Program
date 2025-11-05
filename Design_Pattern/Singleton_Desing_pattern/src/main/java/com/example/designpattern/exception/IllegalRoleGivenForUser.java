package com.designpattern.exception;

public class IllegalRoleGivenForUser extends RuntimeException {
    public IllegalRoleGivenForUser(String message) {
        super(message);
    }
}

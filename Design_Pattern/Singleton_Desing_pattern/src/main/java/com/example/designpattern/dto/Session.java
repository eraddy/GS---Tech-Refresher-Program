package com.designpattern.dto;

public class Session {
    private User user;

    public Session(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}

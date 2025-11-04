package com.main;

import com.designpattern.dto.Role;
import com.designpattern.dto.Session;
import com.designpattern.dto.User;
import com.designpattern.exception.InsufficientRightsException;
import com.designpattern.singleton.SessionManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class SessionManagerTest {
    SessionManager sessionManager = SessionManager.getInstance();

    @Test
    void shouldCreateSessionForValidUser() {
        User user = new User("Aditya", Role.USER);
        Session session = sessionManager.createSession(user, "user access");
        Assertions.assertNotNull(session);
        Assertions.assertEquals(user, session.getUser());
    }

    @Test
    void shouldThrowExceptionForInvalidAccess() {
        User user = new User("Aditya", Role.USER);
        Assertions.assertThrows(InsufficientRightsException.class,
                () -> sessionManager.createSession(user, "admin access"));
    }

    @Test
    void shouldThrowExceptionForNullUser() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> sessionManager.createSession(null, "user access"));
    }
}
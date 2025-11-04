package com.designpattern.singleton;

import com.designpattern.dto.Role;
import com.designpattern.dto.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AccessCheckerTest {

    @Test
    void testSingletonInstance() {
        AccessChecker instance1 = AccessChecker.getInstance();
        AccessChecker instance2 = AccessChecker.getInstance();
        Assertions.assertSame(instance1, instance2, "AccessChecker should return the same instance every time");
    }

    @Test
    void testMayAccessWithValidAccess() {
        User user = new User("Aditya", Role.USER);
        boolean result = AccessChecker.getInstance().mayAccess(user, "user access");
        Assertions.assertTrue(result, "User should be allowed access to their matching access path");
    }

    @Test
    void testMayAccessWithInvalidAccess() {
        User user = new User("Aditya", Role.USER);
        boolean result = AccessChecker.getInstance().mayAccess(user, "admin access");
        Assertions.assertFalse(result, "User should not be allowed to access higher privilege paths");
    }

    @Test
    void testMayAccessForAdmin() {
        User admin = new User("Garvita", Role.ADMIN);
        boolean result = AccessChecker.getInstance().mayAccess(admin, "admin access");
        Assertions.assertTrue(result, "Admin should have access to admin resources");
    }
  
}
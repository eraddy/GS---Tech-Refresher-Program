package com.designpattern.singleton;

import com.designpattern.dto.Role;
import com.designpattern.dto.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ServerConfigTest {

    @Test
    void testSingletonInstance() {
        ServerConfig instance1 = ServerConfig.getInstance();
        ServerConfig instance2 = ServerConfig.getInstance();
        Assertions.assertSame(instance1, instance2, "ServerConfig should return the same instance every time");
    }

    @Test
    void testGetAccessLevelForUserRole() {
        User user = new User("Aditya", Role.USER);
        String level = ServerConfig.getInstance().getAccessLevel(user);
        Assertions.assertEquals("user access", level, "Access level for USER role should match role value");
    }

    @Test
    void testGetAccessLevelForAdminRole() {
        User admin = new User("Garvita", Role.ADMIN);
        String level = ServerConfig.getInstance().getAccessLevel(admin);
        Assertions.assertEquals("admin access", level, "Access level for ADMIN role should match role value");
    }

}
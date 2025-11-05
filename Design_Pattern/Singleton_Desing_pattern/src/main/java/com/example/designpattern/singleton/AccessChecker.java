package com.designpattern.singleton;

import com.designpattern.dto.Role;
import com.designpattern.dto.User;

public class AccessChecker {
    private static final class Holder{
        private static final AccessChecker instance = new AccessChecker();
    }

    private AccessChecker() {}

    public static AccessChecker getInstance() {
        return Holder.instance;
    }

    public boolean mayAccess(User user, String path) {
        return user.getRole().getValue().equals(path);
    }
}
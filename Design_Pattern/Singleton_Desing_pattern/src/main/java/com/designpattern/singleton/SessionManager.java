package com.designpattern.singleton;

import com.designpattern.dto.Session;
import com.designpattern.dto.User;
import com.designpattern.exception.InsufficientRightsException;


public class SessionManager {

    private static final class Holder{
        private static final SessionManager instance = new SessionManager();
    }

    public static SessionManager getInstance(){
        return Holder.instance;
    }

    private SessionManager(){}

    private final AccessChecker access = AccessChecker.getInstance();

    public synchronized Session createSession(User user, String accessedPath) {
        if (user == null || accessedPath == null)
            throw new IllegalArgumentException("User or accessed path cannot be null");

        if (access.mayAccess(user, accessedPath)) {
            return new Session(user);
        } else {
            throw new InsufficientRightsException(user, accessedPath);
        }
    }
}

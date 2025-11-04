package com.designpattern.singleton;

import com.designpattern.dto.User;

public class ServerConfig {

    private static final class Holder{
        private static final ServerConfig instance = new ServerConfig();

    }
    private ServerConfig() {}

    public static ServerConfig getInstance() {
        return Holder.instance;
    }

    public String getAccessLevel(User u) {
        return u.getRole().getValue(); // update as needed
    }
// ...

}

package com.designpattern.dto;

import com.designpattern.exception.IllegalRoleGivenForUser;

public enum Role {
    ADMIN("admin access"),
    USER("user access");

    private final String value;

    Role(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public static Role getRole(String roleDescription){
        for(Role r : Role.values())
        {
            if(r.value.equals(roleDescription))
                return r;
        }
        throw new IllegalRoleGivenForUser("The given role description " + roleDescription + " is not valid");
    }
}

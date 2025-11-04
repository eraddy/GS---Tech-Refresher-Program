package org.example;

import java.time.LocalDate;

public class User {
    String name;
    String userName;
    LocalDate birthDay;

    public User(String name, String userName, LocalDate birthDay) {
        this.name = name;
        this.userName = userName;
        this.birthDay = birthDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", birthDay=" + birthDay +
                '}';
    }
}

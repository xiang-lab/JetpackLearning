package com.xiang.test4;

public class User {

    String user;
    int Id;
    String name;

    public User(String user, String name) {
        this.user = user;
        this.name = name;
    }

    public String getUser(int Id) {
        return user;
    }
}

package com.faf.streaming.models;

public class User {
    int id;
    String nickname;
    String password;

    public User (int id, String nickname, String password) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
    }

    public User() {}
}

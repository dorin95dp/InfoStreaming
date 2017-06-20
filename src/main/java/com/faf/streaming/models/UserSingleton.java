package com.faf.streaming.models;

/**
 * Created by schiduvasile on 6/20/17.
 */
public class UserSingleton {

    private User user;

    private static UserSingleton INSTANCE = null;

    private UserSingleton() {}


    public static UserSingleton getINSTANCE() {
        if(INSTANCE == null) {
            INSTANCE = new UserSingleton();
        }

        return INSTANCE;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}

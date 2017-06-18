package com.faf.streaming.models;

import java.util.HashMap;
import java.util.Map;

public class LoginVerifier {
    private Map<String, String> users = new HashMap<>();

    public LoginVerifier() {
        setUsers();
    }

    private void setUsers() {
        users.put("user", "password");
        users.put("dorin", "1010");
        users.put("dragos", "dragos");
        users.put("vasea", "vasea");
    }

    public boolean userExists (User user) {
        return users.containsKey(user.nickname) && users.get(user.nickname).equals(user.password);
    }

}

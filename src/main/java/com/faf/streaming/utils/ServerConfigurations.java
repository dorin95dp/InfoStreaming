package com.faf.streaming.utils;

public class ServerConfigurations {
    private String ip;
    private static ServerConfigurations instance = null;

    private ServerConfigurations(String ip) {
        this.ip = ip;
    }

    public static ServerConfigurations getInstance(String ip) {
        if (instance == null) {
            instance = new ServerConfigurations(ip);
        }

        return instance;
    }

    public String getIp() {
        return ip;
    }

}

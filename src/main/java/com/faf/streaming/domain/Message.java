package com.faf.streaming.domain;

import java.io.Serializable;

public class Message implements Serializable {

    private int length;
    private String textMessage;

    public Message(int length, String textMessage) {
        this.length = length;
        this.textMessage = textMessage;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }
}

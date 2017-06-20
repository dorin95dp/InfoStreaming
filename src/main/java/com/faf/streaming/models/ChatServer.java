package com.faf.streaming.models;

import com.faf.streaming.utils.MessageReceiver;
import com.faf.streaming.utils.MessageSender;

import java.io.IOException;
import java.net.Socket;
import java.util.HashSet;

public class ChatServer {
    private Socket server;
    private MessageReceiver messageReceiver;
    private HashSet<String> clientIps;

    public ChatServer(Socket server, MessageReceiver messageReceiver, HashSet<String> clientIps) {
        this.server = server;
        this.messageReceiver = messageReceiver;
        this.clientIps = clientIps;
    }

    public void handleChat() {
        new Thread(() -> {
            while (!server.isClosed()){
                try {
                    server.getOutputStream().flush();
                    String message = messageReceiver.receiveMessage();
                    message = message.replace("\0", "");
                    message = message.replace("\n", "");

                    System.out.println(message);

                    for (String clientIp : clientIps) {
                        MessageSender.sendMessage(message, clientIp,1235);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

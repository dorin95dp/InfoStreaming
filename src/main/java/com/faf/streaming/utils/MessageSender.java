package com.faf.streaming.utils;

import com.faf.streaming.controllers.StartViewController;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MessageSender {

    public static void sendMessage(String message, int port) {
        new Thread(() -> {
            try {
                DatagramSocket datagramSocket = new DatagramSocket();
                byte[] buffer = message.getBytes();
                InetAddress receiverAddress = InetAddress.getByName(StartViewController.serverConfig.getIp());
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, receiverAddress, port);
                datagramSocket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

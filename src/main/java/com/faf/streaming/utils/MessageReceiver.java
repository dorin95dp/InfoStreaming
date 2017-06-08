package com.faf.streaming.utils;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class MessageReceiver {



    DatagramSocket datagramSocket = null;
    public MessageReceiver(int port) {
        try {
            datagramSocket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }

    public String receiveMessage() {

        try {

            byte[] buffer = new byte[100];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            datagramSocket.receive(packet);

            return new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}


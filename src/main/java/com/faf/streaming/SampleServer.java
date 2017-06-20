package com.faf.streaming;

import com.faf.streaming.models.ScreenStreamingServer;
import com.faf.streaming.models.ChatServer;
import com.faf.streaming.utils.MessageReceiver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public class SampleServer extends Thread {
    private HashSet<String> clientIps = new HashSet<>();
    private ServerSocket serverSocket;
    private MessageReceiver messageReceiver = new MessageReceiver(1234);
    private int clientConnectionTime = 180000; //miliseconds

    private SampleServer(int port) throws Exception {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(clientConnectionTime);
    }

    public void run() {

        boolean isStopped = false;

        while (!isStopped) {
            try {
                Socket server = serverSocket.accept();
                String clientIp = (((InetSocketAddress)
                        server.getRemoteSocketAddress()).getAddress()).toString().replace("/","");
                clientIps.add(clientIp);

                handleConnection(server);
            } catch (IOException e) {
                System.out.println(e.getMessage() +
                        "\n\nSampleClient didn't connect for" + clientConnectionTime + "minutes");
            }
        }
    }

    private void handleConnection(Socket server) {
        ScreenStreamingServer screenStreamingServer = new ScreenStreamingServer(server);
        ChatServer chatServer = new ChatServer(server, messageReceiver, clientIps);

        screenStreamingServer.handleStreaming();
        chatServer.handleChat();
    }

    public static void main(String [] args) throws Exception {
        Thread t = new SampleServer(6789);
        t.start();
    }
}

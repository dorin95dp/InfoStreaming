package com.faf.streaming;

import com.faf.streaming.utils.MessageReceiver;
import com.faf.streaming.utils.MessageSender;
import com.faf.streaming.utils.ScreenShotMaker;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SampleServer extends Thread {
    private String clientIP;
    private ServerSocket serverSocket;
    private Socket server;
    MessageReceiver messageReceiver = new MessageReceiver(1234);
    int clientConnectionTime = 180000; //miliseconds

    public SampleServer(int port) throws Exception {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(clientConnectionTime);
    }

    public void run() {

        boolean isStopped = false;

        while (!isStopped) {
            try {
                server = serverSocket.accept();
                clientIP=(((InetSocketAddress) server.getRemoteSocketAddress()).getAddress()).toString().replace("/","");

                System.out.println(clientIP);
                handleConnection(server);
            } catch (IOException e) {
                System.out.println(e.getMessage() + "\n\nSampleClient didn't connect for" + clientConnectionTime + "minutes");
            }
        }
    }

    private void handleConnection(Socket server) {
        new Thread(() -> {
            try {
                while (!server.isClosed()) {
                    ImageIO.write(ScreenShotMaker.getINSTANCE().makeScreenShot().getImage(),
                            "JPG", server.getOutputStream());
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            } catch (IOException e) {
                System.out.println(e.getMessage() + "\n\nSampleClient switched off");
            }
        }).start();

        new Thread(() -> {
            while (!server.isClosed()){
                try {
                    server.getOutputStream().flush();
                    String message = messageReceiver.receiveMessage();
                    message = message.replace("\0", "");
                    System.out.println(message);
                    MessageSender.sendMessage(message, clientIP,1235);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String [] args) throws Exception {
        Thread t = new SampleServer(6789);
        t.start();
    }
}

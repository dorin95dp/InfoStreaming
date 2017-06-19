package com.faf.streaming;

import com.faf.streaming.utils.MessageReceiver;
import com.faf.streaming.utils.MessageSender;
import com.faf.streaming.utils.ScreenShotMaker;

import javax.imageio.ImageIO;
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

                    for (String clientIp : clientIps) {
                        MessageSender.sendMessage(message, clientIp,1235);
                    }
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

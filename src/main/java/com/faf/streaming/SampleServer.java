package com.faf.streaming;

import com.faf.streaming.utils.MessageReceiver;
import com.faf.streaming.utils.MessageSender;
import com.faf.streaming.utils.ScreenShotMaker;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SampleServer extends Thread {

    private ServerSocket serverSocket;
    private Socket server;
    MessageReceiver messageReceiver = new MessageReceiver(1234);

    public SampleServer(int port) throws Exception {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(180000);
    }

    public void run() {

        boolean isStopped = false;

        while (!isStopped) {
            try {
                server = serverSocket.accept();
                handleConnection(server);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleConnection(Socket server) {
        new Thread(() -> {
            try {
                while (!server.isClosed()) {
                    ImageIO.write(ScreenShotMaker.getINSTANCE().makeScreenShot().getImage(), "JPG", server.getOutputStream());
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            } catch (IOException e) {
                System.out.println(e.getMessage() + "\n\nClient switched off");
            }
        }).start();

        new Thread(() -> {
            while (!server.isClosed()){
                try {
                    server.getOutputStream().flush();
                    String message = messageReceiver.receiveMessage();
                    message = message.replace("\0", "");
                    System.out.println(message);
                    MessageSender.sendMessage(message, 1235);
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

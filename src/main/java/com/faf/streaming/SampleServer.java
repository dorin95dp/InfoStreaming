package com.faf.streaming;

import com.faf.streaming.utils.ImageStreamReader;
import com.faf.streaming.views.WindowView;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SampleServer extends Thread {

    private ServerSocket serverSocket;
    private Socket server;
    private WindowView windowView = new WindowView();
    private ImageStreamReader imageStreamReader;

    public SampleServer(int port) throws Exception {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(180000);
    }

    public void run() {
        windowView.createWindow();

        try {
            server = serverSocket.accept();
            imageStreamReader = new ImageStreamReader(server.getInputStream());

            imageStreamReader.readImages(receivedImage -> windowView.setImage(receivedImage));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String [] args) throws Exception {
        Thread t = new SampleServer(6789);
        t.start();
    }
}

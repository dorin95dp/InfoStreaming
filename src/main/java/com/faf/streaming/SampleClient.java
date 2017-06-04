package com.faf.streaming;

import com.faf.streaming.utils.ImageStreamReader;
import com.faf.streaming.views.WindowView;

import java.io.IOException;
import java.net.Socket;

public class SampleClient extends Thread {

    private Socket client;
    private static final String serverName = "localhost";
    private ImageStreamReader imageStreamReader;
    private WindowView windowView = new WindowView();

    @Override
    public void run() {
        int port = 6789;

        windowView.createWindow();

        try {
            client = new Socket(serverName, port);

            imageStreamReader = new ImageStreamReader(client.getInputStream());
            imageStreamReader.readImages(receivedImage -> windowView.setImage(receivedImage));

            client.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String [] args) {
        SampleClient client = new SampleClient();
        client.start();
    }
}

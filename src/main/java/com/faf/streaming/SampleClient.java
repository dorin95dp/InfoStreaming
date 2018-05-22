package com.faf.streaming;

import com.faf.streaming.controllers.StartViewController;
import com.faf.streaming.utils.ImageStreamReader;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.Socket;

public class SampleClient extends Thread {

    private Socket client;
    private static final String serverName = StartViewController.serverConfig.getIp();
    private ImageStreamReader imageStreamReader;
    private ImageView imageView;
    private ListView listView;

    public SampleClient(ImageView imageView, ListView listView) {
        this.imageView = imageView;
        this.listView = listView;
    }

    @Override
    public void run() {
        int port = 6789;

        try {
            client = new Socket(serverName, port);
            imageStreamReader = new ImageStreamReader(client.getInputStream(), listView);
            imageStreamReader.readImages(receivedImage -> {
                imageView.setImage(SwingFXUtils.toFXImage(receivedImage, null));
            });

            client.close();
        } catch(Exception e) {
            //
        }
    }
}

package com.faf.streaming;

import com.faf.streaming.controllers.MainWindowController;
import com.faf.streaming.utils.ImageStreamReader;
import com.faf.streaming.utils.OnImageReceivedListener;
import com.faf.streaming.views.LoginView;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.Socket;

public class SampleClient extends Thread {

    private Socket client;
    private static final String serverName = "localhost";
    private ImageStreamReader imageStreamReader;
    private ImageView imageView;

    public SampleClient(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    public void run() {
        int port = 6789;

        try {
            client = new Socket(serverName, port);
            imageStreamReader = new ImageStreamReader(client.getInputStream());
            imageStreamReader.readImages(receivedImage -> {
                imageView.setImage(SwingFXUtils.toFXImage(receivedImage, null));
            });

            client.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


}

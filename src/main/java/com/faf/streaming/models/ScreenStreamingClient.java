package com.faf.streaming.models;

import com.faf.streaming.utils.ImageStreamReader;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.Socket;

public class ScreenStreamingClient {
    private Socket client;
    private ImageView imageView;

    public ScreenStreamingClient(Socket client, ImageView imageView) {
        this.client = client;
        this.imageView = imageView;
    }

    public void handleConnection() {
        try {
            ImageStreamReader imageStreamReader = new ImageStreamReader(client.getInputStream());
            imageStreamReader.readImages(receivedImage ->
                    imageView.setImage(SwingFXUtils.toFXImage(receivedImage, null)));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.faf.streaming.utils;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class ImageStreamReader {

    private static final int MAX_IMAGE_SIZE = 100 * 1024 * 1024;
    private InputStream stream;
    private ListView listView;
    private MessageReceiver messageReceiver = new MessageReceiver(1235);
    private ObservableList<String> chatHistory = FXCollections.observableArrayList ();


    public ImageStreamReader(InputStream inputStream, ListView listView) {
        this.stream = inputStream;
        this.listView = listView;
    }

    public void readImages(OnImageReceivedListener listener) throws IOException {
        stream = new BufferedInputStream(stream);

        boolean isStoppedReading = false;

        new Thread(() -> {
            while (!isStoppedReading){
                String message = messageReceiver.receiveMessage();
                message = message.replace("\0", "");
                String finalMessage = message;
                Platform.runLater(() -> {
                    chatHistory.add(finalMessage);
                    listView.setItems(chatHistory);
                });
            }
        }).start();

        while (!isStoppedReading) {
            stream.mark(MAX_IMAGE_SIZE);

            ImageInputStream imgStream = ImageIO.createImageInputStream(stream);

            Iterator<ImageReader> i = ImageIO.getImageReaders(imgStream);
            if (!i.hasNext()) {
                break;
            }
            ImageReader reader = i.next();
            reader.setInput(imgStream);

            BufferedImage image = reader.read(0);
            if (image == null) {
                break;
            }

            listener.onReceive(image);

            long bytesRead = imgStream.getStreamPosition();
            stream.reset();
            stream.skip(bytesRead);
        }

    }
}

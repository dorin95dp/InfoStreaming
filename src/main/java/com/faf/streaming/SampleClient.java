package com.faf.streaming;

import com.faf.streaming.controllers.StartViewController;
import com.faf.streaming.models.ChatClient;
import com.faf.streaming.models.ScreenStreamingClient;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.Socket;

public class SampleClient extends Thread {

    private ImageView imageView;
    private ListView listView;

    public SampleClient(ImageView imageView, ListView listView) {
        this.imageView = imageView;
        this.listView = listView;
    }

    @Override
    public void run() {
        int port = 6789;
        String serverIp = StartViewController.serverConfig.getIp();

        try {
            Socket client = new Socket(serverIp, port);

            // !!! Very important - the Chat must be handled before screenStreaming, because it is on separate thread
            ChatClient chatClient = new ChatClient(listView);
            ScreenStreamingClient screenStreamingClient = new ScreenStreamingClient(client, imageView);

            chatClient.handleConnection();
            screenStreamingClient.handleConnection();

            client.close();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

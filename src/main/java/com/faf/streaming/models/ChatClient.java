package com.faf.streaming.models;

import com.faf.streaming.utils.MessageReceiver;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class ChatClient {
    private ListView listView;
    private MessageReceiver messageReceiver = new MessageReceiver(1235);
    private ObservableList<String> chatHistory = FXCollections.observableArrayList ();

    public ChatClient(ListView listView) {
        this.listView = listView;
    }

    public void handleConnection() {
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

    }
}

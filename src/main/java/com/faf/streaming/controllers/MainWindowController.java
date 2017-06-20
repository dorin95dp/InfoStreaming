package com.faf.streaming.controllers;

import com.faf.streaming.utils.MessageSender;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;

public class MainWindowController {
    @FXML
    public ListView chatView;
    private ObservableList<String> chatHistory = FXCollections.observableArrayList ();
    @FXML
    public Label lbStatus;
    @FXML
    private TextArea chat;

    public void checkUserInput(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")) {
            String userInput = chat.getText();
            chat.clear();
            chatHistory.add(userInput);
            MessageSender.sendMessage(userInput, StartViewController.serverConfig.getIp(),1234);
        }
    }
}

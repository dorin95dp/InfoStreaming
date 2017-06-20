package com.faf.streaming.controllers;

import com.faf.streaming.models.User;
import com.faf.streaming.models.UserSingleton;
import com.faf.streaming.utils.MessageSender;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class MainWindowController {
    @FXML
    public ListView chatView;



    User user = UserSingleton.getINSTANCE().getUser();

    private String userInput;
    
    private ObservableList<String> chatHistory = FXCollections.observableArrayList ();
    @FXML
    public Label lbStatus;
    @FXML
    private TextArea chat;



    public void checkUserInput(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")) {
            String userInput = chat.getText();
            
            userInput = user.getUsername() + ": " + chat.getText();

            chat.clear();
            chat.backward();
            chatHistory.add(userInput);


            MessageSender.sendMessage(userInput, StartViewController.serverConfig.getIp(),1234);
        }
    }
}

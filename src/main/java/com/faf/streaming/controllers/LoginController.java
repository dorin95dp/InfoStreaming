package com.faf.streaming.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.Executor;


/**
 * Created by schiduvasile on 6/6/17.
 */
public class LoginController {
    @FXML
    private TextField id;
    @FXML
    private TextField pass;
    @FXML
    private Label lbStatus;

    private final static int WIDTH = 422;
    private final static int HEIGHT = 317;


    public void login(ActionEvent event) throws Exception {

        if(id.getText().equals("user") && pass.getText().equals("password")) {
            lbStatus.setText("Login Succesful!");
            switchScene();

        } else{
            lbStatus.setText("Login Failed! Try again");
        }

    }

    private void switchScene() {
        Stage stage;
        Parent root = null;

        stage = (Stage) id.getScene().getWindow();

        try {
            root = FXMLLoader.load(getClass().getResource("/mainView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }


}

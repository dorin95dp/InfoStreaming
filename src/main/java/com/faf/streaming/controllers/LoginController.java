package com.faf.streaming.controllers;

import com.faf.streaming.views.MainView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField id;
    @FXML
    private TextField pass;
    @FXML
    private Label lbStatus;

    public void login() throws Exception {

        if (id.getText().equals("user") && pass.getText().equals("password")) {
            lbStatus.setText("Login Succesful!");
            switchScene();

        } else{
            lbStatus.setText("Login Failed! Try again");
        }

    }

    private void switchScene() {


        Stage stage = (Stage) id.getScene().getWindow();
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("/mainView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();

        MainView mainView = new MainView();
        try {
            mainView.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

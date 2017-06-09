package com.faf.streaming.controllers;

import com.faf.streaming.views.MainView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

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
        } else {
            System.out.println("jora");
            lbStatus.setText("Login Failed! Try again");
        }
    }

    public void enterPressed (KeyEvent keyEvent) {
        if (keyEvent.getCode().toString().equals("ENTER")) {
            try {
                login();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void switchScene() {
        Stage stage = (Stage) id.getScene().getWindow();

        MainView mainView = new MainView();
        try {
            mainView.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

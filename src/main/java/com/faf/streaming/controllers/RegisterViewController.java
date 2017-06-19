package com.faf.streaming.controllers;

import com.faf.streaming.models.RegisterService;
import com.faf.streaming.models.User;
import com.faf.streaming.utils.ServerConfigurations;
import com.faf.streaming.views.StartView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class RegisterViewController {
    public static ServerConfigurations serverConfig = ServerConfigurations.getInstance("localhost");
    private RegisterService registerService = new RegisterService();
    private int id = 1;

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Label lbStatus;

    public void register() throws Exception {
        User user = new User(id, username.getText(), password.getText());

        if (registerService.isUserRegisterd(user)) {
            lbStatus.setText("Register Succesfully");
            id++;
            switchScene();
        } else {
            lbStatus.setText("Register Failed");
        }

    }

    public void changeToLoginScene() throws Exception {
        Stage stage = (Stage) username.getScene().getWindow();

        StartView startView = new StartView();
        try {
            startView.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enterPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().toString().equals("ENTER")) {
            try {
                register();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void switchScene() {
        Stage stage = (Stage) username.getScene().getWindow();

        StartView startView = new StartView();
        try {
            startView.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

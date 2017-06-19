package com.faf.streaming.controllers;

import com.faf.streaming.models.User;
import com.faf.streaming.utils.ServerConfigurations;
import com.faf.streaming.views.MainView;
import com.faf.streaming.views.RegisterView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class StartViewController {
    private LoginVerifier loginVerifier = new LoginVerifier();
    private int id = 1;

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Label lbStatus;
    @FXML
    private TextField serverIp;


    public static ServerConfigurations serverConfig;

    public void login() throws Exception {
        User user = new User(id, username.getText(), password.getText());

        serverConfig = ServerConfigurations.getInstance(serverIp.getText());


        if (loginVerifier.userExists(user)) {
            lbStatus.setText("LoginVerifier Successful");
            id++;
            switchScene();
        } else {
            lbStatus.setText("LoginVerifier Failed! Try again");
        }

    }


    public void enterPressed (KeyEvent keyEvent) {

        if (keyEvent.getCode().toString().equals("ENTER")) {
            try {
                serverConfig = ServerConfigurations.getInstance(serverIp.getText());
                login();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void changeSceneToRegister() {
        Stage stage = (Stage) username.getScene().getWindow();

        RegisterView registerView = new RegisterView();
        try {
            registerView.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void switchScene() {
        Stage stage = (Stage) username.getScene().getWindow();

        MainView mainView = new MainView();
        try {
            mainView.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

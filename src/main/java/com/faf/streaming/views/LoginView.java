package com.faf.streaming.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class LoginView extends Application{

    public void setImage(BufferedImage image) {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/authentificationView.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Login page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}

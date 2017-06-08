package com.faf.streaming.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class LoginView extends Application{
    private JFrame window;
    private JLabel label;
    private final static int WIDTH = 422;
    private final static int HEIGHT = 317;
    public void setImage(BufferedImage image) {
        label.setIcon(new ImageIcon(image));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/authentificationView.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setTitle("Login page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(LoginView.class,args);

    }
}

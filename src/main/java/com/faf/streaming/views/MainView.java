package com.faf.streaming.views;

import com.faf.streaming.SampleClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by schiduvasile on 6/8/17.
 */
public class MainView extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/mainView.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Main window");
        primaryStage.setScene(scene);
        primaryStage.show();

        SampleClient sampleClient = new SampleClient((ImageView) scene.lookup("#boxImage"));
        sampleClient.start();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

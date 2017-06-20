package com.faf.streaming.views;

import com.faf.streaming.SampleClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/mainView.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Main window");
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();

        ListView listView = (ListView) scene.lookup("#chatView") ;

        SampleClient sampleClient = new SampleClient((ImageView) scene.lookup("#boxImage"), listView);
        sampleClient.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

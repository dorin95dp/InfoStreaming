package com.faf.streaming.views;

import com.faf.streaming.SampleClient;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainView extends Application {

    private int start = 0;
    private int step = 4;
    private ObservableList<String> listItems;
    private ObservableList<String> bigData;

    private ScrollBar getListViewScrollBar(ListView<?> listView) {
        ScrollBar scrollbar = null;
        for (Node node : listView.lookupAll(".scroll-bar")) {
            if (node instanceof ScrollBar) {
                ScrollBar bar = (ScrollBar) node;
                if (bar.getOrientation().equals(Orientation.VERTICAL)) {
                    scrollbar = bar;
                }
            }
        }
        return scrollbar;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/mainView.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Main window");
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();

        ListView listView = (ListView) scene.lookup("#chatView") ;

        ScrollBar listViewScrollBar = getListViewScrollBar(listView);

        listViewScrollBar.valueProperty().addListener((observable, oldValue, newValue) -> {
            double position = newValue.doubleValue();
            ScrollBar scrollBar = getListViewScrollBar(listView);
            if (position == scrollBar.getMax()) {
                if (step <= bigData.size()) {
                    listItems.addAll(bigData.subList(start, step));
                    start = step;
                    step += 4;
                }
            }
        });

        SampleClient sampleClient = new SampleClient((ImageView) scene.lookup("#boxImage"), listView);
        sampleClient.start();


    }



    public static void main(String[] args) {
        launch(args);
    }
}

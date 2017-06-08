package com.faf.streaming.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainWindowController {

    @FXML
    public Label lbStatus;

    Image img = new Image("file:flower.jpg");

    @FXML
    ImageView boxImage = new ImageView(img);

}

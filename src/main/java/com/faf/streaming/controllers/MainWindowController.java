package com.faf.streaming.controllers;

import com.faf.streaming.Main;
import com.faf.streaming.SampleClient;
import com.faf.streaming.utils.OnImageReceivedListener;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.image.BufferedImage;

public class MainWindowController {

    @FXML
    public Label lbStatus;

    Image img = new Image("file:flower.jpg");

    @FXML
    ImageView boxImage = new ImageView(img);

}

package com.faf.streaming.views;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class WindowView {
    private JFrame window;
    private JLabel label;

    public void createWindow() {
        window = new JFrame("ScreenSharing");
        window.setSize(800, 600);
        label = new JLabel("Waiting for connection ...");
        window.getContentPane().add(label);
        window.setVisible(true);
    }

    public void setImage(BufferedImage image) {
        label.setIcon(new ImageIcon(image));
    }
}

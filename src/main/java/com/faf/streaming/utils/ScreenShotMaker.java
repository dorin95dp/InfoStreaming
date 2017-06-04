package com.faf.streaming.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScreenShotMaker {
    private static ScreenShotMaker INSTANCE = new ScreenShotMaker();
    private BufferedImage bufferedImage;
    private Robot robot;

    private ScreenShotMaker() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static ScreenShotMaker getINSTANCE() {
        return INSTANCE;
    }

    public ScreenShotMaker makeScreenShot() {
        bufferedImage = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        return this;
    }

    public BufferedImage getImage() {
        return this.bufferedImage;
    }

}

package com.faf.streaming.models;

import com.faf.streaming.utils.ScreenShotMaker;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.Socket;

public class TCPServer {
    private Socket server;

    public TCPServer(Socket server) {
        this.server = server;
    }

    public void handleStreaming() {
        new Thread(() -> {
            try {
                while (!server.isClosed()) {
                    ImageIO.write(ScreenShotMaker.getINSTANCE().makeScreenShot().getImage(),
                            "JPG", server.getOutputStream());
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            } catch (IOException e) {
                System.out.println(e.getMessage() + "\n\nSampleClient switched off");
            }
        }).start();
    }
}

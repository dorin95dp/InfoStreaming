package com.faf.streaming;

import com.faf.streaming.utils.ScreenShotMaker;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.Socket;

public class SampleClient extends Thread {

    private Socket client;
    private static final String serverName = "localhost";

    @Override
    public void run() {
        int port = 6789;

        try {
            client = new Socket(serverName, port);

            while (!client.isClosed()) {
                ImageIO.write(ScreenShotMaker.getINSTANCE().makeScreenShot().getImage(), "JPG", client.getOutputStream());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                client.getOutputStream().flush();
            }

            client.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String [] args) {
        SampleClient client = new SampleClient();
        client.start();
    }
}

package com.faf.streaming;

import com.faf.streaming.utils.ScreenShotMaker;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SampleServer extends Thread {

    private ServerSocket serverSocket;
    private Socket server;

    public SampleServer(int port) throws Exception {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(180000);
    }

    public void run() {
        try {
            server = serverSocket.accept();

            while (!server.isClosed()) {
                ImageIO.write(ScreenShotMaker.getINSTANCE().makeScreenShot().getImage(), "JPG", server.getOutputStream());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                server.getOutputStream().flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String [] args) throws Exception {
        Thread t = new SampleServer(6789);
        t.start();
    }
}

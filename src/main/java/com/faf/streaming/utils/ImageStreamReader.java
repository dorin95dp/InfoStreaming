package com.faf.streaming.utils;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class ImageStreamReader {

    private static final int MAX_IMAGE_SIZE = 100 * 1024 * 1024;
    private InputStream stream;

    public ImageStreamReader(InputStream inputStream) {
        this.stream = inputStream;
    }

    public void readImages(OnImageReceivedListener listener) throws IOException {
        stream = new BufferedInputStream(stream);

        boolean isStoppedReading = false;
        while (!isStoppedReading) {
            stream.mark(MAX_IMAGE_SIZE);

            ImageInputStream imgStream = ImageIO.createImageInputStream(stream);

            Iterator<ImageReader> i = ImageIO.getImageReaders(imgStream);
            if (!i.hasNext()) {
                break;
            }
            ImageReader reader = i.next();
            reader.setInput(imgStream);

            BufferedImage image = reader.read(0);
            if (image == null) {
                break;
            }

            listener.onReceive(image);

            long bytesRead = imgStream.getStreamPosition();
            stream.reset();
            stream.skip(bytesRead);
        }
    }
}

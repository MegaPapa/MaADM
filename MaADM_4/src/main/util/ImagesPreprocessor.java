package main.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

/**
 * Created by User on 29.03.2017.
 */
public class ImagesPreprocessor {

    public static int[] getImagePixels(String imagePath) throws IOException {
        int[] pixels;
        File imgPath = new File(imagePath);
        BufferedImage bufferedImage = ImageIO.read(imgPath);
        pixels = new int[bufferedImage.getHeight() * bufferedImage.getWidth()];
        for (int i = 0; i < bufferedImage.getWidth() - 1; i++) {
            for (int j = 0; j < bufferedImage.getHeight() - 1; j++) {
                if (bufferedImage.getRGB(i, j) == 0xFFFFFFFF) {
                    pixels[i * bufferedImage.getWidth() + j] = 0;
                }
                else {
                    pixels[i * bufferedImage.getWidth() + j] = 1;
                }
            }
        }
        return pixels;
    }
}

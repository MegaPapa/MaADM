package main;

import main.perceptron.Perceptron;
import main.perceptron.TrainingImage;
import main.util.ImagesPreprocessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 29.03.2017.
 */
public class Main {

    public static void main(String[] args) {
        try {
            List<TrainingImage> list = new ArrayList<TrainingImage>();
            for (int i = 0; i < 5; i++) {
                int[] pixels = ImagesPreprocessor.getImagePixels("src/main/resources/" + i + ".jpg");
                System.out.println("ADD " + "src/main/resources/" + i + ".jpg");
                TrainingImage image = new TrainingImage(pixels,1);
                list.add(image);
            }
            for (int i = 5; i < 10; i++) {
                int[] pixels = ImagesPreprocessor.getImagePixels("src/main/resources/" + i + ".jpg");
                System.out.println("ADD " + "src/main/resources/" + i + ".jpg");
                TrainingImage image = new TrainingImage(pixels,0);
                list.add(image);
            }
            Perceptron perceptron = new Perceptron(2500);
            perceptron.trainingPerceptron(list);

            int[] pixels = ImagesPreprocessor.getImagePixels("src/main/resources/ZET.jpg");
            TrainingImage two = new TrainingImage(pixels,0);
            pixels = ImagesPreprocessor.getImagePixels("src/main/resources/ZERO.jpg");
            TrainingImage zero = new TrainingImage(pixels,1);
            System.out.println(perceptron.isFindedImage(two));
            System.out.println(perceptron.isFindedImage(list.get(0)));
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}

package main.perceptron;

import java.util.List;
import java.util.Random;

/**
 * Created by User on 29.03.2017.
 */
public class Perceptron {

    private static final int STEPS_COUNT = 100000;
    private static final byte BLACK_PIXEL_VALUE = 1;

    private int[] weights;

    public Perceptron(int pixelsCount) {
        weights = new int[pixelsCount];
    }

    private void weakConfidence(TrainingImage image) {
        int[] tmp = image.getPixels();
        for (int i = 0; i < image.getPixels().length; i++) {
            if (tmp[i] == BLACK_PIXEL_VALUE) {
                weights[i]--;
            }
        }
    }

    private void strongConfidence(TrainingImage image) {
        int[] tmp = image.getPixels();
        for (int i = 0; i < image.getPixels().length; i++) {
            if (tmp[i] == BLACK_PIXEL_VALUE) {
                weights[i]++;
            }
        }
    }

    public boolean isFindedImage(TrainingImage image) {
        if ((image.calculateWeightSum(weights)) >= 0.0) {
            return true;
        }
        else {
            return false;
        }
    }

    public void trainingPerceptron(List<TrainingImage> list) {
        Random random = new Random();
        int prevPercent = -1;
        for (int i = 0; i < STEPS_COUNT; i++) {
            TrainingImage image = list.get(i % list.size());
            int output;
            if ((image.calculateWeightSum(weights)) >= 0.0) {
                output = 1;
            } else {
                output = 0;
            }

            if (image.getClassIndex() != output) {
                if (image.getClassIndex() == 0) {
                    weakConfidence(image);
                } else {
                    strongConfidence(image);
                }
            }

        }
        for (int i = 0; i < weights.length; i++) {
            System.out.println(weights[i]);
        }
    }
}

package main.perceptron;

/**
 * Created by User on 29.03.2017.
 */
public class TrainingImage {

    private int[] pixels;
    private int classIndex;

    public TrainingImage(int[] pixels, int classIndex) {
        this.pixels = pixels;
        this.classIndex = classIndex;
    }

    public int calculateWeightSum(int[] weights) {
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i] * pixels[i];
        }
        return sum;
    }

    public int[] getPixels() {
        return pixels;
    }

    public void setPixels(int[] pixels) {
        this.pixels = pixels;
    }

    public int getClassIndex() {
        return classIndex;
    }

    public void setClassIndex(int classIndex) {
        this.classIndex = classIndex;
    }
}

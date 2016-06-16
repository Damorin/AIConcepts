package neuralNetwork;

import java.util.ArrayList;
import java.util.List;

/**
 * Add a description of the class here.
 *
 * @author Damien Anderson (Damorin)
 *         16/06/2016
 */
public class BasicImage {

    private List<List<Boolean>> imageGrid = new ArrayList<>();

    public BasicImage(List<List<Boolean>> image) {
        this.imageGrid = image;
    }

    public void displayImage() {
        for(List<Boolean> column : imageGrid) {
            for(Boolean square : column) {
                System.out.print(square + " ");
            }
            System.out.println();
        }
    }
}

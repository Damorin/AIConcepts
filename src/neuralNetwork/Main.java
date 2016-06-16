package neuralNetwork;

import java.util.ArrayList;
import java.util.List;

/**
 * Add a description of the class here.
 *
 * @author Damien Anderson (Damorin)
 *         16/06/2016
 */
public class Main {

    public static void main(String[] args) {

        List<List<Boolean>> image = new ArrayList<>();

        List<Boolean> column = new ArrayList<>();
        column.add(false);
        column.add(false);
        column.add(true);
        column.add(false);
        column.add(false);

        image.add(column);
        column = new ArrayList<>();

        column.add(false);
        column.add(false);
        column.add(true);
        column.add(false);
        column.add(false);

        image.add(column);
        column = new ArrayList<>();

        column.add(false);
        column.add(false);
        column.add(true);
        column.add(false);
        column.add(false);

        image.add(column);
        column = new ArrayList<>();

        column.add(false);
        column.add(false);
        column.add(true);
        column.add(false);
        column.add(false);

        image.add(column);
        column = new ArrayList<>();

        column.add(false);
        column.add(false);
        column.add(true);
        column.add(false);
        column.add(false);

        image.add(column);

        BasicImage input = new BasicImage(image);
        input.displayImage();
    }
}

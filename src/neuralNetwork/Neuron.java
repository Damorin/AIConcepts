package neuralNetwork;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Add a description of the class here.
 *
 * @author Damien Anderson (Damorin)
 *         15/06/2016
 */
public class Neuron {

    private List<Float> inputs;
    private List<Float> weights;
    private Random rng;

    public Neuron(int numOfInputs) {
        inputs = new ArrayList<>(numOfInputs + 1);
        weights = new ArrayList<>(numOfInputs + 1);
        rng = new Random();

        for (int i = 0; i < numOfInputs; i++) {
            weights.add(rng.nextFloat() * 2 - 1);
        }
    }

    private void activation() {
        Float total = 0.0f;
        for (int index = 0; index < inputs.size(); index++) {
            total += inputs.get(index) * weights.get(index);
        }
    }
}

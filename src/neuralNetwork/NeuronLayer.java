package neuralNetwork;

import java.util.ArrayList;
import java.util.List;

/**
 * Add a description of the class here.
 *
 * @author Damien Anderson (Damorin)
 *         15/06/2016
 */
public class NeuronLayer {

    private List<Neuron> neurons;

    public NeuronLayer(int numOfNeurons) {
        neurons = new ArrayList<>(numOfNeurons);
    }

}

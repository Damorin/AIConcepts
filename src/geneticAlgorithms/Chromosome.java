package geneticAlgorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Add a description of the class here.
 *
 * @author Damien Anderson (Damorin)
 *         13/06/2016
 */
public class Chromosome {

    private List<String> binaryString;
    private Random rng;

    public Chromosome() {
        rng = new Random();
        binaryString = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            if(i % 2 == 0) {
                binaryString.add(Integer.toBinaryString(rng.nextInt(4)) + 11);
            }
                else {
                binaryString.add(Integer.toBinaryString(rng.nextInt(11)));
            }
        }
        displayChromosome();
    }

    private void displayChromosome() {
        System.out.println("New Chromosome");
        for (String string : binaryString) {
            System.out.print(string + " ");
        }
        System.out.println();
        decode();
    }

    public float decode() {
        String calculation = "";

        for (String component : binaryString) {
            int componentValue = Integer.parseInt(component, 2);
            if (componentValue >= 0 && componentValue <= 9) {
                calculation = calculation + componentValue;
            } else if (componentValue == 10) {
                calculation = calculation + "+";
            } else if (componentValue == 11) {
                calculation = calculation + "-";
            } else if (componentValue == 12) {
                calculation = calculation + "*";
            } else if (componentValue == 13) {
                calculation = calculation + "/";
            }
        }

        System.out.println("Chromosome calculation: " + calculation);

        return performCalculation(calculation);
    }

    private float performCalculation(String calculation) {
        return 0.0f;
    }

    public void mutate() {

    }

    public void crossover(Chromosome mate) {

    }
}

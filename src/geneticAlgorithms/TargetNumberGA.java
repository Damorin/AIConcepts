package geneticAlgorithms;

import com.sun.org.apache.bcel.internal.generic.POP;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Add a description of the class here.
 *
 * @author Damien Anderson (Damorin)
 *         13/06/2016
 */
public class TargetNumberGA {

    public static final int POPULATION_SIZE = 100;
    private float crossoverRate = 0.7f;
    private float mutationRate = 0.001f;

    private float target;

    private Chromosome correct;
    private List<Chromosome> population;
    private List<Float> populationFitness;

    private Random rng = new Random();

    public TargetNumberGA(float target) {
        this.target = target;
        this.population = new ArrayList<>();
        this.populationFitness = new ArrayList<>();

        this.createPopulation();

        while(!checkForCorrectSolution()) {
            float whatToDo = rng.nextFloat();
            if (whatToDo >= crossoverRate) {

            }
            else if (whatToDo <= mutationRate) {

            }
        }
    }

    private void createPopulation() {
        for(int i = 0; i < POPULATION_SIZE; i++) {
            this.population.add(new Chromosome());
            this.populationFitness.add(Float.MAX_VALUE);
        }
    }

    private boolean checkForCorrectSolution() {
        for (int i = 0; i < POPULATION_SIZE; i++) {
            if(fitnessOf(population.get(i)) == 0) {
                correct = population.get(i);
                return true;
            }
            else {
                populationFitness.add(i, fitnessOf(population.get(i)));
            }
        }
        return false;
    }

    private Float fitnessOf(Chromosome candidate) {
        return 0.0f;
    }
}

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
public class TargetNumberGA {

    public static final int POPULATION_SIZE = 100;
    public static final int BOUND = 44;
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

        this.createInitialPopulation();

        int popCount = 0;

        while (!checkForCorrectSolution()) {
            System.out.println("Population Count: " + popCount++);
            createNewPopulation();
        }
        System.out.println("Done");
        System.out.println("The correct chromosome is : " + correct.getValue() + " " + correct.getCalculation());
    }

    private void createInitialPopulation() {
        System.out.println("First Population");
        for (int i = 0; i < POPULATION_SIZE; i++) {
            Chromosome newChromosome = new Chromosome();
            this.population.add(newChromosome);
            this.populationFitness.add(fitnessOf(newChromosome));
            System.out.println("Chromosome " + i + " created.");
        }
    }

    private boolean checkForCorrectSolution() {
        for (int i = 0; i < POPULATION_SIZE; i++) {
            if (population.get(i).getValue() == target) {
                correct = population.get(i);
                return true;
            } else {
                populationFitness.add(i, fitnessOf(population.get(i)));
            }
        }
        return false;
    }

    private void createNewPopulation() {
        System.out.println("Evolving");
        List<Chromosome> tempList = new ArrayList<>();
        List<Float> tempFitnessList = new ArrayList<>();

        while (tempList.size() < POPULATION_SIZE) {
            Chromosome candidateOne = selectAChromosome();
            Chromosome candidateTwo = selectAChromosome();

            float whatToDo = rng.nextFloat();
            if (whatToDo >= crossoverRate) {
                Chromosome child = crossover(candidateOne, candidateTwo);
                tempList.add(child);
                tempFitnessList.add(fitnessOf(child));
            }
            Chromosome mutation = candidateOne.mutate(mutationRate);
            tempList.add(candidateOne.mutate(mutationRate));
            tempFitnessList.add(fitnessOf(mutation));

            mutation = candidateTwo.mutate(mutationRate);
            tempList.add(candidateTwo.mutate(mutationRate));
            tempFitnessList.add(fitnessOf(mutation));
        }
        this.population.clear();
        this.populationFitness.clear();
        this.population.addAll(tempList);
        this.populationFitness.addAll(tempFitnessList);
    }

    private Chromosome selectAChromosome() {
        int selected = 0;
        double total = populationFitness.get(0);

        for (int i = 1; i < population.size(); i++) {
            total += populationFitness.get(i);
            if (rng.nextDouble() <= (populationFitness.get(i) / total)) {
                selected = i;
            }
        }

        return population.get(selected);
    }

    private Float fitnessOf(Chromosome candidate) {
        return 1 / (target - candidate.getValue());
    }

    public Chromosome crossover(Chromosome firstCandidate, Chromosome secondCandidate) {
        return firstCandidate.crossover(secondCandidate, rng.nextInt(BOUND));
    }
}

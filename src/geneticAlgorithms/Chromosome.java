package geneticAlgorithms;

import org.apache.commons.jexl3.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Add a description of the class here.
 *
 * @author Damien Anderson (Damorin)
 *         13/06/2016
 */
public class Chromosome {

    private static final int BOUND = 44;

    private float value = 0;

    private StringBuilder binaryString = new StringBuilder();
    private String calculation;

    private Random rng;

    public Chromosome() {
        rng = new Random();
        generateString();
        try {
            decode();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Chromosome(StringBuilder binaryString) {
        rng = new Random();
        this.binaryString = binaryString;
        try {
            decode();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateString() {
        List<String> tempList = new ArrayList<>();
        int numOf1 = rng.nextInt(BOUND);
        int numOf0 = BOUND - numOf1;
        for (int i = 0; i < numOf1; i++) {
            tempList.add("1");
        }
        for (int i = 0; i < numOf0; i++) {
            tempList.add("0");
        }
        Collections.shuffle(tempList);
        StringBuilder stringBuilder = new StringBuilder();
        for (String component : tempList) {
            stringBuilder.append(component);
            binaryString.append(stringBuilder.toString());
        }
    }

//    private void displayChromosome() {
//        System.out.println("New Chromosome");
//        System.out.print(binaryString);
//        System.out.println();
//        try {
//            System.out.println(decode());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public float decode() throws Exception {
        boolean wasNumber = false;
        calculation = "";
        while (calculation.length() == 0) {
            for (int i = 0; i < BOUND; i += 4) {
                String component = binaryString.substring(i, i + 4);
                int componentValue = Integer.parseInt(component, 2);
                if (!wasNumber) {
                    if (componentValue >= 0 && componentValue <= 9) {
                        calculation = calculation + componentValue;
                        wasNumber = true;
                    }

                } else {
                    if (componentValue == 10) {
                        calculation = calculation + "+";
                        wasNumber = false;
                    } else if (componentValue == 11) {
                        calculation = calculation + "-";
                        wasNumber = false;
                    } else if (componentValue == 12) {
                        calculation = calculation + "*";
                        wasNumber = false;
                    } else if (componentValue == 13) {
                        calculation = calculation + "/";
                        wasNumber = false;
                    }
                }
            }
            if (calculation.length() == 0) {
                binaryString = new StringBuilder();
                generateString();
            }
        }

        if (!Character.isDigit(calculation.charAt(calculation.length() - 1))) {
            calculation = calculation.substring(0, calculation.length() - 1);
        }
        System.out.println("Chromosome calculation: " + calculation);

        calculateValue();
        return getValue();
    }

    private void calculateValue() {
        JexlEngine jexl = new JexlBuilder().create();
        JexlExpression expression = jexl.createExpression(calculation);
        JexlContext context = new MapContext();
        Object o = expression.evaluate(context);
        value = (Integer) o;
    }

    public Chromosome mutate(float mutationRate) {

        StringBuilder tempString = this.binaryString;
        for (int i = 0; i < binaryString.length(); i++) {
            if (rng.nextFloat() <= mutationRate) {
                tempString.setCharAt(i, (tempString.charAt(i) == '0' ? '1' : '0'));
            }
        }
        return new Chromosome(tempString);
    }

    public Chromosome crossover(Chromosome mate, int point) {
        StringBuilder tempString = new StringBuilder();
        tempString.append(this.binaryString.substring(0, point));
        tempString.append(mate.getBinaryString().substring(point));
        return new Chromosome(tempString);
    }

    private StringBuilder getBinaryString() {
        return this.binaryString;
    }

    public float getValue() {
        return value;
    }

    public String getCalculation() { return calculation; }
}

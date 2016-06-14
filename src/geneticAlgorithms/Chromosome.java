package geneticAlgorithms;

import org.apache.commons.jexl3.*;

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
            binaryString.add(String.format("%4s", Integer.toBinaryString(rng.nextInt(14))).replace(" ", "0"));
        }
        displayChromosome();
    }

    private void displayChromosome() {
        System.out.println("New Chromosome");
        for (String string : binaryString) {
            System.out.print(string + " ");
        }
        System.out.println();
        try {
            System.out.println(decode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer decode() throws Exception {
        String calculation = "";

        boolean wasNumber = false;
        for (String component : binaryString) {
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

        if (!Character.isDigit(calculation.charAt(calculation.length() - 1))) {
            calculation = calculation.substring(0, calculation.length() - 1);
        }
        System.out.println("Chromosome calculation: " + calculation);

        return performCalculation(calculation);
    }

    private Integer performCalculation(String calculation) {
        JexlEngine jexl = new JexlBuilder().create();
        JexlExpression expression = jexl.createExpression(calculation);
        JexlContext context = new MapContext();
        Object o = expression.evaluate(context);
        return (Integer) o;
    }

    public void mutate(float mutationRate) {
        for(String component : binaryString) {
            for (int i = 0; i < 4; i++) {
                if (rng.nextFloat() <= mutationRate) {
                    if (component.charAt(i) == '0') {
                    }
                }
            }
        }
    }

    public void crossover(Chromosome mate) {

    }
}

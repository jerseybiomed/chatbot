package banditbot;

import logic.Calculator;

import java.util.Arrays;
import java.util.List;

/**
 * RouletteDrum
 */
public class RouletteDrum {
    private int[] drum = new int[] {0, 32, 15, 19, 4, 21, 2, 25, 17, 34, 6, 27, 13, 36, 11, 30, 8, 23, 10, 5, 24, 16,
        33, 1, 20, 14, 31, 9, 22, 18, 29, 7, 28, 12, 35, 3, 26};
    private List<Integer> red = Arrays.asList(32, 19, 21, 25, 34, 27, 36, 30, 23, 5, 16, 1, 14, 9, 18, 7, 12, 3);
    private List<Integer> black = Arrays.asList(15, 4, 2, 17, 6, 13, 11, 8, 10, 24, 33, 20, 31, 22, 29, 28, 35, 26);
    private Calculator calculator;

    public RouletteDrum(Calculator calculator) { this.calculator = calculator; }

    public double roll() {
        return calculator.getNextNumber();
    }

    public String getColor(int number) {
        return red.contains(number) ? "red" : black.contains(number) ? "black" : "green";
    }
}

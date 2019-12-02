package onearmedbandit;

import java.io.IOException;

import logic.Calculator;

/**
 * Drum
 */
public class Drum {
    private String currentComb = "000";

    public double roll(final int bet) throws IOException {
        this.currentComb = Calculator.getCombination(this.currentComb);
        return bet * Calculator.getCoffisient(this.currentComb);
    }

    public String getComb() {
        return this.currentComb;
    }
}
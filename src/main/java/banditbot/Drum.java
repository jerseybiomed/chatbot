package banditbot;

import java.io.IOException;

import logic.Calculator;

/**
 * Drum
 */
public class Drum {
    private String currentComb = "000";
    private Calculator calculator;
    
    public Drum(Calculator calculator) {
    	this.calculator = calculator;
    }

    public double roll(int bet) throws IOException {
        this.currentComb = calculator.getCombination(this.currentComb);
        return bet * calculator.getCoffisient(this.currentComb);
    }

    public String getComb() {
        return this.currentComb;
    }
}
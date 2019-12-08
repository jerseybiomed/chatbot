package bot.banditbot;

import java.io.IOException;

import logic.Calculator;

/**
 * Drum
 */
public class Drum {
    private final Calculator calculator;
    private String currentComb = "000";

    public Drum(final Calculator m_calculator) {
        this.calculator = m_calculator;
    }

    public double roll(final int bet) throws IOException {
        this.currentComb = this.calculator.getCombination(this.currentComb);
        return bet * this.calculator.getCoffisient(this.currentComb);
    }

    public String getComb() {
        return this.currentComb;
    }
}
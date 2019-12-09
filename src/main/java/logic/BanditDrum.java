package logic;

/**
 * BanditDrum
 */
public class BanditDrum {
    private String currentComb = "000";
    private Calculator calculator;
    
    public BanditDrum(Calculator calculator) {
    	this.calculator = calculator;
    }

    public double roll(int bet) {
        currentComb = calculator.getCombination(this.currentComb);
        return bet * calculator.getCoefficient(this.currentComb);
    }

    public String getComb() {
        return currentComb;
    }
}
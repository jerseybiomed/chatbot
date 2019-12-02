import java.io.IOException;

import logic.Calculator;

public class Main {

	public static void main(String[] args) throws IOException {
		String combination = Calculator.getCombination("000");
		System.out.println(combination);
		System.out.print(100 * Calculator.getCoffisient(combination));
	}

}

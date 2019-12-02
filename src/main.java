import java.io.IOException;

import logic.Calculator;

public class main {

	public static void main(String[] args) throws IOException {
		String combination = Calculator.getCombination("000");
		System.out.println(combination);
		System.out.print(75 * Calculator.getCoffisient(combination));
	}

}

import java.io.IOException;

import logic.Calculator;

public class main {

	public static void main(String[] args) throws IOException {
		Calculator calculator = new Calculator();
		String combination = calculator.getCombination("000");
		System.out.println(combination);
		System.out.print(100 * calculator.getCoffisient(combination));
	}

}

package logic;

import web.Connector;

import java.io.IOException;

public class Calculator {

	public static String getCombination(final String previousComb) throws IOException {
		final String request = "https://www.random.org/integers/?num=1&min=1&max=50&col=1&base=10&format=plain&rnd=new";
		String combination = "";
		for (int i = 0; i < 3; i++)
			combination += ((int) previousComb.charAt(0) + Connector.GetRandomNumber(request)) % 10;
		return combination;
	}

	public static double getCoffisient(final String combination) {
		final Combinator combinator = new Combinator();
		if (combinator.isFirstClassWinnerComb(combination)) 
			return 2;
		else if (combinator.isSecondClassWinnerComb(combination))
			return 1.5;
		else if (combinator.isBeautifulComb(combination))
			return 1;
		return 0;
	}
}

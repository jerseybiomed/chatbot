package logic;

import java.io.IOException;

import web.Connector;

public class Calculator {
	
	public String getCombination(String previousComb) throws IOException {
		Connector connector = new Connector();
		String request = "https://www.random.org/integers/?num=1&min=1&max=50&col=1&base=10&format=plain&rnd=new";
		String combination = "";
		for (int i=0; i < 3; i++)
			combination += ((int)previousComb.charAt(0) + connector.GetRandomNumber(request)) % 10;
		return combination;
	}
	
	public double getCoffisient(String combination) {
		Ñombinator combinator = new Ñombinator();
		if (combinator.isFirstClassWinnerComb(combination)) 
			return 2;
		else if (combinator.isSecondClassWinnerComb(combination))
			return 1.5;
		else if (combinator.isBeautifulComb(combination))
			return 1;
		return 0;
	}
}

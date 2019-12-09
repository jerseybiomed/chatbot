package logic;

import java.io.IOException;

import web.Connector;

public class Calculator {

	public String getCombination(String previousComb) {
		String request = "https://www.random.org/integers/?num=10&min=1&max=50&col=1&base=10&format=plain&rnd=new";
		String combination = "";
		for (int i=0; i < 3; i++) {
			try {
				int random = Connector.GetRandomNumber(request);
				combination += (Integer.parseInt(previousComb.substring(i, i + 1)) + random) % 10;
			} catch (IOException e){
				e.printStackTrace();
			}
		}
		return combination;
	}
	
	public double getCoffisient(String combination) {
		Combinator combinator = new Combinator();
		if (combinator.isFirstClassWinnerComb(combination)) 
			return 2;
		else if (combinator.isSecondClassWinnerComb(combination))
			return 1.5;
		else if (combinator.isBeautifulComb(combination))
			return 1;
		return 0;
	}

	public int getNextNumber() {
		String request = "https://www.random.org/integers/?num=10&min=0&max=36&col=1&base=10&format=plain&rnd=new";
		int random = 0;
		try {
			random =  Connector.GetRandomNumber(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return random;
	}
}

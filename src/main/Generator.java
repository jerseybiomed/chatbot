package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Generator {
	
	private ArrayList<Turn> turns = new ArrayList<Turn>();
	private Turn loseTurn = new Turn("ß ñäàşñü, òû âûéãğàë");
	private String alph = "ÀÁÂÃÄÅ¨ÆÇÈÊËÌÍÎÏĞÑÒÓÔÕÖ×ØÙİŞß";
	
	public Generator(String source) {
		generateFromFile(source);
	}
	
	private void generateFromFile(String file) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String city;
			while ((city = reader.readLine()) != null) {
				if (alph.indexOf(city.charAt(0)) != -1) {
					turns.add(new Turn(city));
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Turn nextTurn(String city) {
		Turn answer = new Turn(city);
		for (int i = 0; i < turns.size(); i++) {
			if (Character.toUpperCase(answer.getNextLetter()) == turns.get(i).getPreviousLetter()) {
				Turn nextTurn = turns.get(i);
				turns.remove(i);
				return nextTurn;
			}
		}
		return loseTurn;
	}
	
	public boolean checkAnswer(String city) {
		int length = turns.size();
		for (int i = 0; i < length; i++) {
			if (turns.get(i).getCity().equals(city)) {
				turns.remove(i);
				return true;
			}
		}
		return true;
	}
}

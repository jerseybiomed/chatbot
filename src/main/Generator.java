package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Generator {
	
	private ArrayList<Turn> turns = new ArrayList<Turn>();
	private Turn loseTurn = new Turn("Я сдаюсь, ты выйграл");
	
	public Generator(String file) {
		generateFromFile(file);
	}
	
	private void generateFromFile(String file) {
		try {
			var reader = new BufferedReader(new FileReader(file));
			String city;
			while ((city = reader.readLine()) != null) {
				turns.add(new Turn(city));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Turn nextTurn(String city) {
		int length = turns.size();
		for (int i = 0; i < length; i++) {
			if (Character.toUpperCase(city.charAt(city.length()-1)) == turns.get(i).getPreviousLetter()) {
				Turn nextTurn = turns.get(i);
				turns.remove(i);
				return nextTurn;
			}
		}
		return loseTurn;
	}
	
}

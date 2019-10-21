package main;

import java.util.Arrays;

public class Turn {

	private char previousLetter;
	private char nextLetter;
	private String city;
	private char[] badLastLetters = {'ú','ü','û','é'};
	
	public Turn(String city) {
		this.city = city;
		previousLetter = city.charAt(0);
		nextLetter = city.charAt(city.length() - 1);
		if (Arrays.asList(badLastLetters).contains(nextLetter)) {
			nextLetter = city.charAt(city.length() - 2);
		}
	}
	
	public char getPreviousLetter() {
		return previousLetter;
	}
	
	public String getCity() {
		return city;
	}
	
	public char getNextLetter() {
		return nextLetter;
	}
	
}

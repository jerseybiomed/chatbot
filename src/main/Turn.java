package main;

public class Turn {

	private char previousLetter;
	private char nextLetter;
	private String city;
	private String badLastLetters = "éûüú";
	
	public Turn(String city) {
		this.city = city;
		previousLetter = city.charAt(0);
		nextLetter = city.charAt(city.length() - 1);
		if (badLastLetters.indexOf(nextLetter) != -1) {
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

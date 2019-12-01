package logic;

public class Ñombinator {
	
	public boolean isFirstClassWinnerComb(String combination) {
		return combination.charAt(0) == combination.charAt(1) && combination.charAt(0) == combination.charAt(2);
	}
	
	public boolean isSecondClassWinnerComb(String combination) {
		return combination.charAt(0) == combination.charAt(1) || 
				combination.charAt(0) == combination.charAt(2) ||
				combination.charAt(1) == combination.charAt(2);
	}
	
	public boolean isBeautifulComb(String combination) {
		return ((int)combination.charAt(2) - (int)combination.charAt(1)) == 
				((int)combination.charAt(1) - (int)combination.charAt(0)) ||
				((int)combination.charAt(0) - (int)combination.charAt(1)) == 
				((int)combination.charAt(1) - (int)combination.charAt(2));
	}

}

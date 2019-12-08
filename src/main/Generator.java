package main;

public class Generator {
	
	public int getCombination(int startPosition) {
		int firstReelStart = startPosition / 100;
		int secondReelStart = startPosition / 10 % 10;
		int thirdReelStart = startPosition % 10;
		int firstReelChange = (int) (9 * Math.random());
		int secondReelChange = (int) (9 * Math.random());
		int thirdReelChange = (int) (9 * Math.random());
		return ((firstReelStart + firstReelChange) % 9 + 1)* 100 +
				((secondReelStart + secondReelChange) % 9 + 1)* 10 +
				((thirdReelStart + thirdReelChange) % 9 + 1);
	}

}
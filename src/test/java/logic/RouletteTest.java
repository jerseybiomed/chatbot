package logic;

import static org.junit.jupiter.api.Assertions.*;

import game.Roulette;
import org.junit.jupiter.api.Test;

class RouletteTest {
	Roulette roulette = new Roulette();

	@Test
	void testColorFirst() {
		assertEquals(roulette.getColor(32), "red");
	}
	
	@Test
	void testColorSecond() {
		assertEquals(roulette.getColor(15), "black");
	}
	
	@Test
	void testColorThird() {
		assertEquals(roulette.getColor(0), "green");
	}
	
	@Test
	void testCoefficientFirst() {
		assertEquals(roulette.getCoefficient(0, "0"), 36);
		assertEquals(roulette.getCoefficient(32, "32"), 36);
		assertEquals(roulette.getCoefficient(15, "15"), 36);
	}
	
	@Test
	void testCoefficientSecond() {
		assertEquals(roulette.getCoefficient(32, "red"), 2);
		assertEquals(roulette.getCoefficient(15, "black"), 2);
	}
	
	@Test
	void testCoefficientThird() {
		assertEquals(roulette.getCoefficient(15, "red"), 0);
		assertEquals(roulette.getCoefficient(32, "black"), 0);
		assertEquals(roulette.getCoefficient(0, "25"), 0);
	}

}

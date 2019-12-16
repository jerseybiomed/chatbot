package logic;

import static org.junit.jupiter.api.Assertions.*;

import game.Roulette;
import org.junit.jupiter.api.Test;
import web.Randomize;

class RouletteTest {
	Randomize randomize = new Randomize();
	Roulette roulette = new Roulette(randomize);

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

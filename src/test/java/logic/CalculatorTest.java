package logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CalculatorTest {
	Calculator calculator = new Calculator();
	
	@Test
	public void combinationRangeTestFirst() {
		String combination = calculator.getCombination("000");
		assertEquals(combination.length(), 3);
		assertTrue(0 <= Integer.parseInt(combination) && Integer.parseInt(combination) <= 999);
	}
	
	@Test
	public void combinationRangeTestSecond() {
		String combination = calculator.getCombination("999");
		assertEquals(combination.length(), 3);
		assertTrue(0 <= Integer.parseInt(combination) && Integer.parseInt(combination) <= 999);
	}
	
	@Test
	public void coffisientTestFirst() {
		assertEquals(calculator.getCoefficient("000"), 2);
	}
	
	@Test
	public void coffisientTestSecond() {
		assertEquals(calculator.getCoefficient("110"), 1.5);
	}
	
	@Test
	public void coffisientTestThird() {
		assertEquals(calculator.getCoefficient("123"), 1);
	}
	
	@Test
	public void coffisientTestFourth() {
		assertEquals(calculator.getCoefficient("321"), 1);
	}
	
	@Test
	public void coffisientTestFifth() {
		assertEquals(calculator.getCoefficient("971"), 0);
	}
	
	@Test
	public void coffisientTestSixth() {
		assertEquals(calculator.getCoefficient("011"), 1.5);
	}
	
	@Test
	public void coffisientTestSeventh() {
		assertEquals(calculator.getCoefficient("101"), 1.5);
	}

}

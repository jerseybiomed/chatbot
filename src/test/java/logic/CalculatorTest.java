package logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import logic.Calculator;

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
		assertEquals(calculator.getCoffisient("000"), 2);
	}
	
	@Test
	public void coffisientTestSecond() {
		assertEquals(calculator.getCoffisient("110"), 1.5);
	}
	
	@Test
	public void coffisientTestThird() {
		assertEquals(calculator.getCoffisient("123"), 1);
	}
	
	@Test
	public void coffisientTestFourth() {
		assertEquals(calculator.getCoffisient("321"), 1);
	}
	
	@Test
	public void coffisientTestFifth() {
		assertEquals(calculator.getCoffisient("971"), 0);
	}
	
	@Test
	public void coffisientTestSixth() {
		assertEquals(calculator.getCoffisient("011"), 1.5);
	}
	
	@Test
	public void coffisientTestSeventh() {
		assertEquals(calculator.getCoffisient("101"), 1.5);
	}

}

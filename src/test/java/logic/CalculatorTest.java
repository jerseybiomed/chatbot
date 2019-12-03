package logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import logic.Calculator;

class CalculatorTest {

	@Test
	public void combinationRangeTestFirst() throws IOException {
		String combination = Calculator.getCombination("000");
		assertEquals(combination.length(), 3);
		assertTrue(0 <= Integer.parseInt(combination) && Integer.parseInt(combination) <= 999);
	}
	
	@Test
	public void combinationRangeTestSecond() throws IOException {
		String combination = Calculator.getCombination("999");
		assertEquals(combination.length(), 3);
		assertTrue(0 <= Integer.parseInt(combination) && Integer.parseInt(combination) <= 999);
	}
	
	@Test
	public void coffisientTestFirst() throws IOException {
		assertEquals(Calculator.getCoffisient("000"), 2);
	}
	
	@Test
	public void coffisientTestSecond() throws IOException {
		assertEquals(Calculator.getCoffisient("110"), 1.5);
	}
	
	@Test
	public void coffisientTestThird() throws IOException {
		assertEquals(Calculator.getCoffisient("123"), 1);
	}
	
	@Test
	public void coffisientTestFourth() throws IOException {
		assertEquals(Calculator.getCoffisient("321"), 1);
	}
	
	@Test
	public void coffisientTestFifth() throws IOException {
		assertEquals(Calculator.getCoffisient("971"), 0);
	}
	
	@Test
	public void coffisientTestSixth() throws IOException {
		assertEquals(Calculator.getCoffisient("011"), 1.5);
	}
	
	@Test
	public void coffisientTestSeventh() throws IOException {
		assertEquals(Calculator.getCoffisient("101"), 1.5);
	}

}

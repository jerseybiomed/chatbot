package test;

import main.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestFailure {

	public double getCoffisient(int combination) {
		Bot bot = new Bot();
		return bot.getCoffisient(combination);
	}
	
	@Test
	public void test1() {
		assertEquals(2.0, getCoffisient(777));
	}
	
	@Test
	public void test2() {
		assertEquals(1.5, getCoffisient(771));
	}
	
	@Test
	public void test3() {
		assertEquals(1.5, getCoffisient(717));
	}
	
	@Test
	public void test4() {
		assertEquals(1.5, getCoffisient(177));
	}
	
	@Test
	public void test5() {
		assertEquals(0, getCoffisient(123));
	}
}

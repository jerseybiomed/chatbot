package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bot.CommandRegistry;

class CommandRegistryTest {
	
	@Test
	public void addTest() {
		CommandRegistry<Integer> test = new CommandRegistry<Integer>();
		test.add("a", 1);
		test.add("b", 2);
		test.add("c", 3);
		assertEquals(test.get("a"), 1);
		assertEquals(test.get("b"), 2);
		assertEquals(test.get("c"), 3);
	}
	
	@Test
	public void containsTest() {
		CommandRegistry<Integer> test = new CommandRegistry<Integer>();
		test.add("a", 1);
		test.add("b", 2);
		test.add("c", 3);
		assertTrue(test.contains("a") && test.contains("b") && test.contains("c") && !test.contains("d"));
	}
	
	@Test
	public void removeTest() {
		CommandRegistry<Integer> test = new CommandRegistry<Integer>();
		test.add("a", 1);
		test.add("b", 2);
		test.add("c", 3);
		assertTrue(test.contains("c"));
		test.remove("c");
		assertFalse(test.contains("c"));
		test.remove("a");
		assertFalse(test.contains("a"));
	}
	
	@Test
	public void replaceTest() {
		CommandRegistry<Integer> test = new CommandRegistry<Integer>();
		test.add("a", 1);
		test.add("b", 2);
		test.add("c", 3);
		assertEquals(test.get("a"), 1);
		assertEquals(test.get("b"), 2);
		test.replace("a", 2);
		test.replace("b", 1);
		assertEquals(test.get("a"), 2);
		assertEquals(test.get("b"), 1);
	}

}

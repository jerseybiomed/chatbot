package web;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class RandomizeTest {
	Randomize randomize = new Randomize();

	@Test
	public void rangeTestFirst() throws IOException {
		String request = "https://www.random.org/integers/?num=1&min=1&max=2&col=1&base=10&format=plain&rnd=new";
		assertTrue(1 <= randomize.Next(request) && randomize.Next(request) <= 2);
	}
	
	@Test
	public void rangeTestSecond() throws IOException {
		String request = "https://www.random.org/integers/?num=1&min=1&max=5&col=1&base=10&format=plain&rnd=new";
		assertTrue(1 <= randomize.Next(request) && randomize.Next(request) <= 5);
	}
	
	@Test
	public void rangeTestThird() throws IOException {
		String request = "https://www.random.org/integers/?num=1&min=5&max=10&col=1&base=10&format=plain&rnd=new";
		assertTrue(5 <= randomize.Next(request) && randomize.Next(request) <= 10);
	}
	
	@Test
	public void rangeTestFourth() throws IOException {
		String request = "https://www.random.org/integers/?num=1&min=0&max=25&col=1&base=10&format=plain&rnd=new";
		assertTrue(0 <= randomize.Next(request) && randomize.Next(request) <= 25);
	}
	
	@Test
	public void rangeTestFifth() throws IOException {
		String request = "https://www.random.org/integers/?num=1&min=1&max=50&col=1&base=10&format=plain&rnd=new";
		assertTrue(1 <= randomize.Next(request) && randomize.Next(request) <= 50);
	}

}

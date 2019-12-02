package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import web.Connector;

class ConnectorTest {

	@Test
	public void rangeTestFirst() throws IOException {
		String request = "https://www.random.org/integers/?num=1&min=1&max=2&col=1&base=10&format=plain&rnd=new";
		assertTrue(1 <= Connector.GetRandomNumber(request) && Connector.GetRandomNumber(request) <= 2);
	}
	
	@Test
	public void rangeTestSecond() throws IOException {
		String request = "https://www.random.org/integers/?num=1&min=1&max=5&col=1&base=10&format=plain&rnd=new";
		assertTrue(1 <= Connector.GetRandomNumber(request) && Connector.GetRandomNumber(request) <= 5);
	}
	
	@Test
	public void rangeTestThird() throws IOException {
		String request = "https://www.random.org/integers/?num=1&min=5&max=10&col=1&base=10&format=plain&rnd=new";
		assertTrue(5 <= Connector.GetRandomNumber(request) && Connector.GetRandomNumber(request) <= 10);
	}
	
	@Test
	public void rangeTestFourth() throws IOException {
		String request = "https://www.random.org/integers/?num=1&min=0&max=25&col=1&base=10&format=plain&rnd=new";
		assertTrue(0 <= Connector.GetRandomNumber(request) && Connector.GetRandomNumber(request) <= 25);
	}
	
	@Test
	public void rangeTestFifth() throws IOException {
		String request = "https://www.random.org/integers/?num=1&min=1&max=50&col=1&base=10&format=plain&rnd=new";
		assertTrue(1 <= Connector.GetRandomNumber(request) && Connector.GetRandomNumber(request) <= 50);
	}

}

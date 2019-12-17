package logic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import logic.dialog.StartLogic;

class StartLogicTest {
	
	@Test
	void testStartLogic() {
		StartLogic startLogic = new StartLogic();
		assertEquals(StartLogic.startRequest(), "Choose your game");
	}

}

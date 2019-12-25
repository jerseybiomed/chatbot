package logic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.Bandit;
import logic.dialog.BanditLogic;
import logic.telegram.Player;
import web.Randomize;

class BanditLogicTest {
	BanditLogic banditLogic = new BanditLogic();

	@Test
	void testBanditRequest() {
		Player player = new Player();
		assertEquals(player.getBanditBalance(), 0);
		String help = banditLogic.banditRequest(player);
		assertEquals(player.getBanditBalance(), 10000);
		assertEquals(help, Bandit.Help);
	}
	
	@Test
	void testBackRequest() {
		assertEquals(banditLogic.backRequest(), "Choose your game");
	}
	
	@Test 
	void testRulesRequest() {
		assertEquals(banditLogic.rulesRequest(), Bandit.Rules);
	}
	
	@Test
	void testHelpRequest() {
		assertEquals(banditLogic.helpRequest(), Bandit.Help);
	}
	
	@Test 
	void testRollRequest() {
		Bandit bandit = new Bandit(new Randomize(1));
		Player player0 = new Player();
		Player player1 = new Player();
		player0.setBanditBalance(1000);
		player1.setBanditBalance(0);
		assertEquals(banditLogic.rollRequest(player1, "0", bandit), 
				"Current line: 111" + " result: 0" + "\nCurrent balance: 0" +
                "\nYou lost all money\nThe guards kicked you out\nGood Luck and Have Fun!");
		assertEquals(banditLogic.rollRequest(player0, "10000", bandit),
				"Not enough balance\nCurrent balance: 1000");
		assertEquals(banditLogic.rollRequest(player0, "100", bandit),
				"Current line: 222" + " result: 300" + "\nCurrent balance: 1200");
	}

}

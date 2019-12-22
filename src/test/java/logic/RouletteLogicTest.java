package logic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.Roulette;
import logic.dialog.RouletteLogic;
import logic.telegram.Player;
import web.Randomize;

class RouletteLogicTest {
	Roulette roulette = new Roulette(new Randomize(1));
	TestBot bot = new TestBot(roulette);
	Player player1 = new Player();
	Player player2 = new Player();
	Player player3 = new Player();
	Player player4 = new Player();
	Player player5 = new Player();
	Player player6 = new Player();
	Player player7 = new Player();
	Player player8 = new Player();
	Player player9 = new Player();
	Player player10 = new Player();
	Player player11 = new Player();

	@Test
	void testRouletteRequest() {
		RouletteLogic rouletteLogic = bot.getRouletteLogic();
		assertEquals(rouletteLogic.rouletteRequest(player1, roulette), Roulette.Help);
		rouletteLogic.rouletteRequest(player2, roulette);
		rouletteLogic.rouletteRequest(player3, roulette);
		rouletteLogic.rouletteRequest(player4, roulette);
		rouletteLogic.rouletteRequest(player5, roulette);
		rouletteLogic.rouletteRequest(player6, roulette);
		rouletteLogic.rouletteRequest(player7, roulette);
		rouletteLogic.rouletteRequest(player8, roulette);
		rouletteLogic.rouletteRequest(player9, roulette);
		rouletteLogic.rouletteRequest(player10, roulette);
		assertEquals(rouletteLogic.rouletteRequest(player11, roulette), "Not enough seats");
	}
	
	@Test
	void testBackRequest() {
		RouletteLogic rouletteLogic = bot.getRouletteLogic();
		assertEquals(rouletteLogic.backRequest(player1, roulette), "Choose your game");
		rouletteLogic.rouletteRequest(player2, roulette);
		rouletteLogic.rouletteRequest(player3, roulette);
		rouletteLogic.rouletteRequest(player4, roulette);
		rouletteLogic.rouletteRequest(player5, roulette);
		rouletteLogic.rouletteRequest(player6, roulette);
		rouletteLogic.rouletteRequest(player7, roulette);
		rouletteLogic.rouletteRequest(player8, roulette);
		rouletteLogic.rouletteRequest(player9, roulette);
		rouletteLogic.rouletteRequest(player10, roulette);
		assertEquals(rouletteLogic.rouletteRequest(player11, roulette), Roulette.Help);
		assertEquals(rouletteLogic.rouletteRequest(player1, roulette), "Not enough seats");
	}
	
	@Test
	void testBet() {
		RouletteLogic rouletteLogic = bot.getRouletteLogic();
		rouletteLogic.rouletteRequest(player1, roulette);
		rouletteLogic.rouletteRequest(player2, roulette);
		String[] bet = new String[] {"/bet", "red", "100"};
		rouletteLogic.betRequest(player1, bet, roulette);
		assertEquals(rouletteLogic.checkResult(player1, "32", roulette), "32 RED\nYou win: 200" + "\n Current balance: 10100");
		assertEquals(rouletteLogic.checkResult(player2, "32", roulette), "32 RED");
	}
	
	@Test
	void testRulesReruest() {
		RouletteLogic rouletteLogic = bot.getRouletteLogic();
		assertEquals(rouletteLogic.rulesRequest(), Roulette.Rules);
	}
	
	@Test
	void testHelpRequest() {
		RouletteLogic rouletteLogic = bot.getRouletteLogic();
		assertEquals(rouletteLogic.helpRequest(), Roulette.Help);
	}

}

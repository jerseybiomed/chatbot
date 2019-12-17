package game;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.HashSet;

import game.Roulette;
import logic.telegram.Player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import web.Randomize;

class RouletteTest {
	Randomize randomize;
	Roulette roulette;
	
	@BeforeEach
	void init() {
		randomize = new Randomize();
		roulette = new Roulette(randomize);
	}

	@Test
	void testPlayers() {
		Player player0 = new Player();
		Player player1 = new Player();
		player1.setRouletteBalance(9000);
		HashSet<Player> emptyPlayers = roulette.getPlayers();
		roulette.addPlayer(player0);
		HashSet<Player> countOnePlayers = roulette.getPlayers();
		roulette.addPlayer(player1);
		HashSet<Player> countTwoPlayers = roulette.getPlayers();
		roulette.removePlayer(player0);
		roulette.removePlayer(player1);
		HashSet<Player> removePlayers = roulette.getPlayers();
		assertEquals(emptyPlayers.size(), 0);
		assertEquals(countOnePlayers.size(), 1);
		assertEquals(countTwoPlayers.size(), 2);
		assertEquals(removePlayers.size(), 0);
		assertFalse(emptyPlayers.contains(player0));
		assertTrue(countOnePlayers.contains(player0));
		assertTrue(countTwoPlayers.contains(player1) 
				&& countTwoPlayers.contains(player0));
		assertFalse(removePlayers.contains(player0));
		assertEquals(player0.getRouletteBalance(), 10000);
		assertEquals(player1.getRouletteBalance(), 9000);
	}
	
	@Test
	void testAddBets() {
		Player player = new Player();
		roulette.addPlayer(player);
		HashMap<Player, String[]> emptyBets = roulette.getBets();
		String[] firstBet = new String[] {"black", "100"};
		roulette.bet(player, firstBet);
		HashMap<Player, String[]> firstBets = roulette.getBets();
		String[] secondBet = new String[] {"red", "1000"};
		roulette.bet(player, secondBet);
		HashMap<Player, String[]> secondBets = roulette.getBets();
		assertEquals(emptyBets.size(), 0);
		assertEquals(firstBets.size(), 1);
		assertEquals(secondBets.size(), 1);
		assertTrue(firstBets.containsKey(player) 
				&& firstBets.containsValue(firstBet)
				&& firstBets.get(player) == firstBet);
		assertTrue(secondBets.containsKey(player) 
				&& secondBets.containsValue(secondBet) 
				&& !secondBets.containsValue(firstBet));
	}
	
	@Test
	void testResultBets() {
		Player player0 = new Player();
		Player player1 = new Player();
		Player player2 = new Player();
		String[] firstBet = new String[] {"black", "100"};
		roulette.bet(player0, firstBet);
		String[] secondBet = new String[] {"red", "1000"};
		roulette.bet(player1, secondBet);
		String[] thirdBet = new String[] {"15", "10"};
		roulette.bet(player2, thirdBet);
		assertEquals(roulette.getBets().size(), 3);
		assertEquals(roulette.betResult(player0, firstBet, 15), 200);
		assertEquals(roulette.getBets().size(), 2);
		assertEquals(roulette.betResult(player1, secondBet, 15), 0);
		assertEquals(roulette.getBets().size(), 1);
		assertEquals(roulette.betResult(player2, thirdBet, 15), 360);
		assertEquals(roulette.getBets().size(), 0);
	}
	
	@Test
	void testColorFirst() {
		assertEquals(roulette.getColor(32), "red");
	}
	
	@Test
	void testColorSecond() {
		assertEquals(roulette.getColor(15), "black");
	}
	
	@Test
	void testColorThird() {
		assertEquals(roulette.getColor(0), "green");
	}
	
	@Test
	void testCoefficientFirst() {
		assertEquals(roulette.getCoefficient(0, "0"), 36);
		assertEquals(roulette.getCoefficient(32, "32"), 36);
		assertEquals(roulette.getCoefficient(15, "15"), 36);
	}
	
	@Test
	void testCoefficientSecond() {
		assertEquals(roulette.getCoefficient(32, "red"), 2);
		assertEquals(roulette.getCoefficient(15, "black"), 2);
	}
	
	@Test
	void testCoefficientThird() {
		assertEquals(roulette.getCoefficient(15, "red"), 0);
		assertEquals(roulette.getCoefficient(32, "black"), 0);
		assertEquals(roulette.getCoefficient(0, "25"), 0);
	}

}

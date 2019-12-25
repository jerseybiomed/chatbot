package game;

import static org.junit.jupiter.api.Assertions.*;

import java.util.AbstractMap.SimpleEntry;

import org.junit.jupiter.api.Test;

import logic.telegram.Player;
import web.Randomize;

class BanditTest {

	@Test
	void testBandit() {
		Player player = new Player();
		player.setBanditBalance(10000);
		Randomize randomize = new Randomize(1);
		Bandit bandit = new Bandit(randomize);
		SimpleEntry<String, Integer> result = bandit.roll(player, 1000);
		assertEquals(result.getKey(), "111");
		assertEquals(result.getValue(), 3000);
	}

}

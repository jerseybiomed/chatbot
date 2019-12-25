package games.bandit;

import org.junit.jupiter.api.Test;

import customer.CustomerState;
import random.Randomize;

/**
 * TestBandit
 */
public class TestBandit {

    @Test
    void testGameplay() {
        BanditClient game = new BanditClient(new CustomerState(), new Randomize());
        game.getHelp();
        game.getRules();
        game.bet(100);
        game.roll();
        game.getCombination();
        game.getCoefficient();
        game.back();
    }
}
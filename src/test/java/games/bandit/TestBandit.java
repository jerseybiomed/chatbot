package games.bandit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import customer.ConsoleCustomer;
import customer.CustomerState;
import random.Randomize;

/**
 * TestBandit
 */
public class TestBandit {

    @Test
    void testGameplay() {
        BanditClient game = new BanditClient(new CustomerState(new ConsoleCustomer("/\\ioxa"), 10000),
                new Randomize());
        game.getHelp();
        game.getRules();
        game.bet(100);
        game.roll();
        game.getCombination();
        game.getCoefficient();
        game.back();
    }

    @Test
    void testBet() {
        BanditClient game = new BanditClient(new CustomerState(new ConsoleCustomer("lexa"), 10000),
                new Randomize());
        game.bet(100);
        int bet = game.getBet();
        assertEquals(bet, 100);
    }

    @Test
    void testRoll() {
        BanditClient game = new BanditClient(new CustomerState(new ConsoleCustomer("lexa"), 10000),
                new Randomize());
        game.bet(100);
        int result = game.roll();
        String combination = game.getCombination();
        int coefficient = game.getCoefficient();
        if (coefficient == 0)
            assertEquals(result, 0);
        else if (coefficient == 2)
            assertEquals(result, 200);
    }
}
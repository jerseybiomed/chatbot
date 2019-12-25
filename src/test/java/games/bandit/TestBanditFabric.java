package games.bandit;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import customer.CustomerState;
import games.GameFabric;
import random.Randomize;

/**
 * TestBanditFabric
 */
public class TestBanditFabric {

    @Test
    void testNewBandit() {
        GameFabric fabric = new BanditFabric(new Randomize());
        CustomerState state = new CustomerState();
        assertNotEquals(fabric.newGame(state), fabric.newGame(state));
    }
}
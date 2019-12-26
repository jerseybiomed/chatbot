package games.roulette;

import customer.ConsoleCustomer;
import customer.CustomerState;
import org.junit.jupiter.api.Test;
import random.Randomize;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRoulette {

    @Test
    void testBetOne() {
        RouletteClient rouletteClient = new RouletteClient(new CustomerState(
            new ConsoleCustomer("lexa"),10000), new Roulette(new Randomize()));
        rouletteClient.bet(100, "red");
        rouletteClient.sayResult(32);
        int result = rouletteClient.getWin();
        assertEquals(result, 200);
    }

    @Test
    void testBetTwo() {
        RouletteClient rouletteClient = new RouletteClient(new CustomerState(
                new ConsoleCustomer("lexa"),10000), new Roulette(new Randomize()));
        rouletteClient.bet(100, "0");
        rouletteClient.sayResult(0);
        int result = rouletteClient.getWin();
        assertEquals(result, 3600);
    }
}

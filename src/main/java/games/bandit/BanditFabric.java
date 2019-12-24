package games.bandit;

import customer.CustomerState;
import games.GameClient;
import games.GameFabric;
import random.Randomize;

/**
 * BanditFabric
 */
public class BanditFabric
extends GameFabric<BanditClient> {
    private Randomize randomize;

    public BanditFabric(Randomize randomize) {
        this.randomize = randomize;
    }

    @Override
    public BanditClient newGame(GameClient from, CustomerState state) {
        return new BanditClient(from, state, randomize);
    }

    @Override
    public BanditClient newGame(CustomerState state) {
        return new BanditClient(state, randomize);
    }

    @Override
    public String getGameName() {
        return "bandit";
    }    
}
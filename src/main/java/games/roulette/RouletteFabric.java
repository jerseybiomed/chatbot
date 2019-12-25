package games.roulette;

import customer.CustomerState;
import games.GameClient;
import games.GameFabric;

/**
 * RouletteFabric
 */
public class RouletteFabric
extends GameFabric<RouletteClient> {
    private Roulette roulette;

    public RouletteFabric(Roulette roulette) {
        this.roulette = roulette;
    }

    @Override
    public RouletteClient newGame(GameClient from, CustomerState state) {
        return new RouletteClient(from, state, roulette);
    }
}
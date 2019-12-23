package games.simplegame;

import customer.CustomerState;
import games.GameClient;
import games.GameFabric;

/**
 * SimpleGameFabric
 */
public class SimpleGameFabric
extends GameFabric<SimpleGameClient> {

    @Override
    public SimpleGameClient newGame(GameClient from, CustomerState state) {
        return new SimpleGameClient(from, state);
    }

    @Override
    public String getGameName() {
        return "simpleGame";
    }
}
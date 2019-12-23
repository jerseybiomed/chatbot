package games.simplegame;

import customer.CustomerState;
import games.GameFabricSuper;

/**
 * SimpleGameFabric
 */
public class SimpleGameFabric
extends GameFabricSuper<SimpleGameClient> {

    @Override
    public SimpleGameClient newGame(CustomerState state) {
        return new SimpleGameClient(this.menu, state);
    }

    @Override
    public String getGameName() {
        return "simpleGame";
    }
}
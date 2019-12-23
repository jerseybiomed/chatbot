package games;

import customer.CustomerState;

/**
 * GameFabric
 */
public abstract class GameFabric<T extends GameClient> {

    public abstract T newGame(final CustomerState state);
    public abstract String getGameName();
}
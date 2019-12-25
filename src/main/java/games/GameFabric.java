package games;

import customer.CustomerState;

/**
 * GameFabric
 */
public abstract class GameFabric<T extends GameClient> {

    public T newGame(final GameClient from, final CustomerState state) { return null; }
    public T newGame(final CustomerState state) { return null; }
    public String getGameName() {
        return T.getName();
    }
}
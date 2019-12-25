package games.simplegame;

import customer.CustomerState;
import games.GameClient;

/**
 * SimpleGameClient
 */
public class SimpleGameClient
extends GameClient {

    public SimpleGameClient(final GameClient from, final CustomerState exState) {
        super(from, exState);
    }

    public static String getName() {
        return "simpleGame";
    }

    @Override
    public String getHelp() {
        return "simple help";
    }

    @Override
    public int getBalance() {
        return state.exState.balance;
    }

    @Override
    public String getGameName() {
        return getName();
    }
}
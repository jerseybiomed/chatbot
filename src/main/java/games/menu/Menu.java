package games.menu;

import java.util.HashMap;

import customer.CustomerState;
import games.GameClient;
import games.GameFabric;

/**
 * Menu
 */
public class Menu
extends GameClient {
    private final String help = "help of this bot";
    protected final HashMap<String, GameFabric> games = new HashMap<>();

    public Menu(final CustomerState state, final GameFabric... fabrics) {
        super(state);
        for (final GameFabric fabric : fabrics) {
            this.games.put(fabric.getGameName(), fabric);
        }
    }

	public static String getName() {
        return "menu";
	}

    @Override
    public String getHelp() {
        return this.help;
    }

    @Override
    public int getBalance() {
        return state.exState.balance;
    }

    public void choose(final String choice) {
        if (games.containsKey(choice)) {
            state.exState.focus = games.get(choice).newGame(this, state.exState);
        }
    }

    @Override
    public String getGameName() {
        return getName();
    }
}
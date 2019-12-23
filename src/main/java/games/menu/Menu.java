package games.menu;

import customer.CustomerState;
import games.GameClient;
import games.GameFabricSuper;
import task.TaskCreator;

import java.util.HashMap;
import java.util.function.Function;

/**
 * Menu
 */
public class Menu
extends GameClient {
    private final TaskCreator<Menu> creator;
    private final String help = "help of this bot";
    protected final HashMap<String, Function<CustomerState, GameClient>> games = new HashMap<>();

    public Menu(final TaskCreator<Menu> m_creator, final CustomerState m_exState, final GameFabricSuper... fabrics) {
        super(m_exState);
        this.creator = m_creator;
        for (GameFabricSuper fabric : fabrics) {
            fabric.setMenu(this);
            this.games.put(fabric.getGameName(), fabric::newGame);
        }
    }

    @Override
    public TaskCreator getTaskCreator() {
        return this.creator;
    }

    @Override
    public String getHelp() {
        return this.help;
    }

    public void choose(final String choice) {
        this.exState.setGame(this.games.get(choice).apply(this.exState));
    }
}
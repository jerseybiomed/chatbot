package games.menu;

import java.util.HashMap;

import customer.CustomerState;
import games.GameClient;
import games.GameFabric;
import task.TaskCreator;

/**
 * Menu
 */
public class Menu
extends GameClient {
    private final TaskCreator<Menu> creator;
    private final String help = "help of this bot";
    protected final HashMap<String, GameFabric> games = new HashMap<>();

    public Menu(final TaskCreator<Menu> m_creator, final CustomerState m_exState, final GameFabric... fabrics) {
        super(m_exState);
        this.creator = m_creator;
        for (GameFabric fabric : fabrics) {
            this.games.put(fabric.getGameName(), fabric);
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
        if (games.containsKey(choice))
            this.state.focus.setGame(this.games.get(choice).newGame(this, this.state.focus));
    }
}
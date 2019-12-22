package bot;

import java.util.HashMap;
import java.util.function.Function;

/**
 * Menu
 */
public class Menu
extends Game {
    private final TaskCrafter<Menu> crafter;
    protected final CustomerState externalState; 
    private final String help = "help of this bot";
    protected final HashMap<String, Function<CustomerState, Game>> games = new HashMap<>();

    public Menu(final TaskCrafter<Menu> m_crafter, final CustomerState m_state, final GameFabric... fabrics) {
        this.crafter = m_crafter;
        this.externalState = m_state;
        for (GameFabric fabric : fabrics) {
            this.games.put(fabric.getGameName(), fabric::newGame);
        }
    }

    @Override
    public TaskCrafter getTaskCrafter() {
        return this.crafter;
    }

    @Override
    public String getHelp() {
        return this.help;
    }

    public void choose(final String choice) {
        this.externalState.setGame(this.games.get(choice).apply(this.externalState));
    }
}
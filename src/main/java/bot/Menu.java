package bot;

import java.util.HashMap;
import java.util.function.Function;

/**
 * Menu
 */
public class Menu
extends Game {
    private final TaskCrafter<Menu> crafter;
    private final String help = "help of this bot";
    protected final HashMap<String, Function<CustomerState, Game>> games = new HashMap<>();

    public Menu(final TaskCrafter<Menu> m_crafter, final CustomerState m_exState, final GameFabricSuper... fabrics) {
        super(m_exState);
        this.crafter = m_crafter;
        for (GameFabricSuper fabric : fabrics) {
            fabric.setMenu(this);
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
        this.exState.setGame(this.games.get(choice).apply(this.exState));
    }
}
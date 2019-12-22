package bot;

/**
 * Menu
 */
public class Menu
extends Game {
    private final TaskCrafter crafter;
    private final String help = "help of this bot";

    public Menu(final TaskCrafter m_crafter) {
        this.crafter = m_crafter;
    }

    @Override
    public TaskCrafter getTaskCrafter() {
        return this.crafter;
    }

    @Override
    public String getHelp() {
        return this.help;
    }
}
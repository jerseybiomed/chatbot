package bot;

/**
 * Game
 */
public abstract class Game {
    protected final Game from;
    protected final CustomerState exState;

    public Game(final CustomerState m_exState) {
        this.from = this;
        this.exState = m_exState;
    }

    public Game(final Game m_from, final CustomerState m_exState) {
        this.from = m_from;
        this.exState = m_exState;
    }

    public abstract TaskCrafter<Game> getTaskCrafter();
    public abstract String getHelp();
    public void back() {
        exState.setGame(this.from);
    }
}
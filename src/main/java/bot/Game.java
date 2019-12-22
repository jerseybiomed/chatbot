package bot;

/**
 * Game
 */
public abstract class Game {
    protected final Game from;

    public Game() {
        this.from = this;
    }

    public Game(final Game m_from) {
        this.from = m_from;
    }

    public abstract TaskCrafter<Game> getTaskCrafter();
    public abstract String getHelp();

    public void name() {
        
    }
}
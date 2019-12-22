package bot;

/**
 * CustomerState
 */
public class CustomerState {
    public TaskCrafter crafter;
    public Game game;

    public CustomerState(final Game game) {
        this.setGame(game);
    }
    
    public void setGame(final Game m_game) {
        this.game = m_game;
        this.crafter = m_game.getTaskCrafter();
    }
}

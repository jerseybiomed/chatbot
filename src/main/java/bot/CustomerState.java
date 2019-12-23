package bot;

/**
 * CustomerState
 */
public class CustomerState {
    public TaskCrafter<Game> crafter;
    public Game game;
    
    public void setGame(final Game m_game) {
        this.game = m_game;
        this.crafter = m_game.getTaskCrafter();
    }
}

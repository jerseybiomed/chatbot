package customer;

import games.GameClient;
import task.TaskCreator;

/**
 * CustomerState
 */
public class CustomerState {
    private TaskCreator<GameClient> creator;
    private GameClient currentGameClient;
    
    public void setGame(final GameClient m_gameClient) {
        this.currentGameClient = m_gameClient;
        this.creator = m_gameClient.getTaskCreator();
    }

    public GameClient getGame() {
        return this.currentGameClient;
    }

    public TaskCreator<GameClient> getCreator() {
        return this.creator;
    }
}

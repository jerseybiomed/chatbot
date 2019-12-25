package games;

import customer.CustomerState;
import task.TaskCreator;

/**
 * GameClient
 */
public abstract class GameClient {
    protected final GameClient from;
    protected final CustomerState exState;

    public GameClient(final CustomerState m_exState) {
        this.from = this;
        this.exState = m_exState;
    }

    public GameClient(final GameClient m_from, final CustomerState m_exState) {
        this.from = m_from;
        this.exState = m_exState;
    }

    public abstract TaskCreator<GameClient> getTaskCreator();
    public abstract String getHelp();
    public void back() {
        exState.setGame(this.from);
    }
}
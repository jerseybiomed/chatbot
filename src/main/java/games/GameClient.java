package games;

import customer.CustomerState;
import task.TaskCreator;

/**
 * GameClient
 */
public abstract class GameClient {
    protected final GameClient from;
    protected final GameState state;

    public GameClient(final CustomerState state) {
        this.from = this;
        this.state = new GameState(state);
    }

    public GameClient(final GameClient m_from, final CustomerState state) {
        this.from = m_from;
        this.state = new GameState(state);
    }

    public abstract TaskCreator<GameClient> getTaskCreator();
    public abstract String getHelp();
    public void back() {
        state.focus.setGame(this.from);
    }

    public static String getName() {
        return "game";
    }
}
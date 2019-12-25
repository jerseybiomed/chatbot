package games;

import customer.CustomerState;

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

    public abstract String getHelp();
    public abstract int getBalance();
    public void back() {
        state.exState.focus = this.from;
    }

    public abstract String getGameName();
}
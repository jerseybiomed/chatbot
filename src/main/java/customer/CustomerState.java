package customer;

import java.util.HashMap;

import games.GameClient;
import task.TaskCreator;

/**
 * CustomerState
 */
public class CustomerState {
    private final HashMap<String, TaskCreator> creators = new HashMap<>();
    private GameClient game;
    
    public void setGame(final GameClient gameClient) {
        this.game = gameClient;
        if (!creators.containsKey(gameClient.getGameName()))
            creators.put(gameClient.getGameName(), gameClient.getTaskCreator());
    }

    public GameClient getGame() {
        return game;
    }

    public TaskCreator getCreator() {
        return creators.get(game.getGameName());
    }
}

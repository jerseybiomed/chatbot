package games.simplegame;

import customer.CustomerState;
import games.GameClient;
import task.DefaultTaskCreator;
import task.Task;
import task.TaskCreator;

/**
 * SimpleGameClient
 */
public class SimpleGameClient
extends GameClient {

    public SimpleGameClient(final GameClient from, final CustomerState exState) {
        super(from, exState);
    }

    @Override
    public TaskCreator<GameClient> getTaskCreator() {
        return new TaskCreator<GameClient>(){
        
            @Override
            public Task<GameClient> create(final String[] args) {
                return new DefaultTaskCreator().create(args);
            }
        };
    }

    @Override
    public String getHelp() {
        return "simple help";
    }

    @Override
    public String getGameName() {
        return "simpleGame";
    }
}
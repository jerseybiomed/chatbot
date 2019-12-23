package task;

import games.GameClient;
import logic.Sender;

/**
 * DefaultTaskCrafter
 */
public class DefaultTaskCreator
extends TaskCreator<GameClient> {
    
    protected class TaskHelp
    extends Task<GameClient> {

        @Override
        public void perform(final GameClient gameClient, final Sender sender) {
            sender.send(gameClient.getHelp());
        }
    }

    protected class TaskBack
    extends Task<GameClient> {
    
        @Override
        public void perform(final GameClient gameClient, final Sender sender) {
            gameClient.back();
        }
    }

    @Override
    public Task<GameClient> create(final String[] args) {
        switch (args[0]) {
            case "help":
                return new TaskHelp();
            case "back":
                return new TaskBack();
        }
        return new EmptyTask();
    }
}
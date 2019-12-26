package task;

import java.util.HashMap;

import games.GameClient;
import games.bandit.BanditClient;
import games.menu.Menu;
import games.roulette.RouletteClient;
import games.simplegame.SimpleGameClient;
import logic.Sender;

/**
 * DefaultTaskCreator
 */
public class DefaultTaskCreator
extends TaskCreator<GameClient> {
    
    private static HashMap<String, TaskCreator> defaultTaskCreators = new HashMap<String, TaskCreator>() {{
        put(Menu.getName(), new MenuTaskCreator());
        put(BanditClient.getName(), new BanditTaskCreator());
        put(RouletteClient.getName(), new RouletteTaskCreator());
        put(SimpleGameClient.getName(), new DefaultTaskCreator());
    }};

    public static TaskCreator<GameClient> getDefaultTaskCreator(String gameName) {
        TaskCreator<GameClient> creator = defaultTaskCreators.get(gameName);
        return creator;
    }

    protected class TaskBalance
    extends Task<GameClient> {

        @Override
        public void perform(final GameClient gameClient, final Sender<String> sender) {
            sender.send(Integer.toString(gameClient.getBalance()));
        }
    }

    protected class TaskHelp
    extends Task<GameClient> {

        @Override
        public void perform(final GameClient gameClient, final Sender<String> sender) {
            sender.send(gameClient.getHelp());
        }
    }

    protected class TaskBack
    extends Task<GameClient> {
    
        @Override
        public void perform(final GameClient gameClient, final Sender<String> sender) {
            gameClient.back();
            sender.send("Use /help to know where you now");
        }
    }

    @Override
    public Task<GameClient> create(final String[] args) {
        switch (args[0]) {
            case "help":
                return new TaskHelp();
            case "balance":
                return new TaskBalance();
            case "back":
                return new TaskBack();
        }
        return new EmptyTask();
    }
}
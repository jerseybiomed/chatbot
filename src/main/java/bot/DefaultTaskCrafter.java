package bot;

/**
 * DefaultTaskCrafter
 */
public class DefaultTaskCrafter
extends TaskCrafter<Game> {
    protected class TaskHelp
    extends Task<Game> {

        @Override
        public void perform(final Game game, final Sender sender) {
            sender.send(game.getHelp());
        }
    }

    @Override
    public Task<Game> craft(final String[] args) {
        switch (args[0]) {
            case "help":
                return new TaskHelp();
        }
        return new EmptyTask();
    }
}
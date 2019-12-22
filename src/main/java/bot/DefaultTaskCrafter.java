package bot;

/**
 * DefaultTaskCrafter
 */
public class DefaultTaskCrafter
extends TaskCrafter {
    protected class TaskHelp
    extends Task {

        @Override
        public void perform(Game game, Sender sender) {
            sender.send(game.getHelp());
        }
    }

    @Override
    public Task craft(final String[] args) {
        switch (args[0]) {
            case "help":
                return new TaskHelp();
        }
        return new EmptyTask();
    }
}
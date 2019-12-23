package bot;

/**
 * SimpleGame
 */
public class SimpleGame
extends Game {

    public SimpleGame(final Game from, final CustomerState exState) {
        super(from, exState);
    }

    @Override
    public TaskCrafter<Game> getTaskCrafter() {
        return new TaskCrafter<Game>(){
        
            @Override
            public Task<Game> craft(final String[] args) {
                return new DefaultTaskCrafter().craft(args);
            }
        };
    }

    @Override
    public String getHelp() {
        return "simple help";
    }
}
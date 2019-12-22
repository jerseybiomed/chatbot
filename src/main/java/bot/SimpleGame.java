package bot;

/**
 * SimpleGame
 */
public class SimpleGame
extends Game {
    protected final CustomerState customer;

    public SimpleGame(final CustomerState m_state) {
        this.customer = m_state;
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
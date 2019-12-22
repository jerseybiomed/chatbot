package bot;

/**
 * TaskCrafter
 */
public abstract class TaskCrafter<T extends Game> {

    public abstract Task<T> craft(final String[] args);
}
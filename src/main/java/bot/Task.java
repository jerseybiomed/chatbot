package bot;

/**
 * Task
 */
public abstract class Task<T extends Game> {

    public abstract void perform(final T game, final Sender replySender);
}
package task;

import games.GameClient;
import logic.Sender;

/**
 * Task
 */
public abstract class Task<T extends GameClient> {

    public abstract void perform(final T game, final Sender replySender);
}
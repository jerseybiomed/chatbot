package task;

import games.GameClient;

/**
 * TaskCreator
 */
public abstract class TaskCreator<T extends GameClient> {

    public abstract Task<T> create(final String[] args);
}
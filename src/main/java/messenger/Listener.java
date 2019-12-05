package messenger;

import messenger.Connector.Connectable;

/**
 * Listener
 */
public abstract class Listener<T> implements Connectable{
    protected abstract void start(final Object source);
    public abstract void listen(final T arg);
}
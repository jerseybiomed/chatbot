package messenger;

/**
 * Listener
 */
public interface Listener<T> {
    void listen(final T arg);
}
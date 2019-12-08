package connection.messegestream;

/**
 * Listener
 */
public interface Listener<T> {
    void listen(T data);
    void start();
}
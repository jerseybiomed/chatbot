package connection;

/**
 * Connectable
 */
public interface Connectable<T> {
    void connect(T connection);
}
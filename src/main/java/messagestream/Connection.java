package messagestream;

/**
 * Connection
 */
public interface Connection<T> {
    void connect(T item);
}
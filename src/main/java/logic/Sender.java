package logic;

/**
 * Sender
 */
public interface Sender<T> {

    void send(T message);
}
package bot;

/**
 * ISubscriber
 */
public interface ISubscriber<T> {

    void listen(T msg);
}
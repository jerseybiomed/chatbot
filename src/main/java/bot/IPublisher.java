package bot;

/**
 * IPublisher
 */
public interface IPublisher<T> {

    void pub(ISubscriber<T> sub);
}
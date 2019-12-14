package messagestream.speakers;

/**
 * Speaker
 */
public interface Speaker<T> {
    void say(T message);    
}
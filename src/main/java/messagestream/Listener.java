package messagestream;

import messagestream.speakers.Speaker;

/**
 * Listener
 */
public interface Listener<T> {
    void listen(Speaker<T> from, T data);
}
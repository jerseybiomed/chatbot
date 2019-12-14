package messagestream.translator;

import messagestream.Listener;
import messagestream.speakers.Speaker;

/**
 * Translator
 */
public abstract class Translator<T>
implements Listener<T>, Speaker<T> {    
}
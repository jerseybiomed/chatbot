package messagestream.translator;

/**
 * Console
 */
public class Console
extends Translator<String> {
    @Override
    public void listen(final String message) {
        this.say(message);
    }

    @Override
    public void start() {
    }

    @Override
    public void say(final String message) {
        System.out.println(message);
    }
}
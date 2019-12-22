package bot;

/**
 * ConsoleSender
 */
public class ConsoleSender
implements Sender {

    @Override
    public void send(String message) {
        System.out.println(message);
    }
}
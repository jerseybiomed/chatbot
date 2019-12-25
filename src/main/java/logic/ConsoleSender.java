package logic;

/**
 * ConsoleSender
 */
public class ConsoleSender
implements Sender<String> {

    @Override
    public void send(String message) {
        System.out.println(message);
    }
}
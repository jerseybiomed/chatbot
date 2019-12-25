package logic.console;

import logic.Sender;

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
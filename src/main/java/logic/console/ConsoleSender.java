package logic.console;

import logic.Sender;

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
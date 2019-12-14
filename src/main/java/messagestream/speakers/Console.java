package messagestream.speakers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import messagestream.Connection;
import messagestream.Listener;

/**
 * Console
 */
public class Console
implements Runnable, Speaker<String>, Connection<Listener<String>> {
    private final Scanner scan;
    private final List<Listener<String>> listeners = new ArrayList<Listener<String>>();

    public Console() {
        this.scan = new Scanner(System.in);
    }

    @Override
    public void run() {
        while (true) {
            this.say(this.scan.nextLine());
        }
    }

    @Override
    public void say(final String message) {
        for (final Listener<String> cl : this.listeners)
            cl.listen(message);
    }

	@Override
	public void connect(Listener<String> listener) {
        this.listeners.add(listener);
	}
}
package connection.messegestream;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Console
 */
public class Console implements Runnable {
    private final Scanner scan;
    private final List<Listener<String>> listeners = new ArrayList<Listener<String>>();

    public Console(final InputStream in) {
        this.scan = new Scanner(in);
    }

    @Override
    public void run() {
        while (true) {
            final String line = this.scan.nextLine();
            for (final Listener<String> cl : this.listeners)
                cl.listen(line);
        }
    }

    public void addListener(final Listener<String> listener) {
        this.listeners.add(listener);
    }
}
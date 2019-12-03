package messenger;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Console
 */
public class Console implements Runnable {
    private final Scanner scan;
    private final List<Listener<String>> listeners = new ArrayList<Listener<String>>();

    public Console(final InputStream in, final OutputStream out) {
        this.scan = new Scanner(in);
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
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
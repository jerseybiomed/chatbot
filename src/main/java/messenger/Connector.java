package messenger;

/**
 * Connector
 */
public class Connector {
    public interface Connectable {
        void connect(Connectable item);
    }

    public static void connect(Connectable item1, Connectable item2) {
        item1.connect(item2);
        item2.connect(item1);
    }
}
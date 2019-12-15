package bot.command;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Registry
 */
public class Registry<T> {

    private ConcurrentSkipListMap<String, T> map = new ConcurrentSkipListMap<>();
    
    public void add(final String commandName, final T commandFunc) {
        Registry.assertArgNotNull(commandFunc);
        this.map.put(commandName, commandFunc);
    }

    public boolean contains(final String commandName) {
        Registry.assertArgNotNull(commandName);
        this.assertMapNotNull();
        return this.map.containsKey(commandName) &&
            !Registry.isNull(this.map.get(commandName));
    }

    public T get(final String commandName) {
        this.assertContainsName(commandName);
        return this.map.get(commandName);
    }

    public void remove(final String commandName) {
        this.assertContainsName(commandName);
        this.map.remove(commandName);
    }

    public void replace(final String commandName, final T commandFunc) {
        this.assertContainsName(commandName);
        Registry.assertArgNotNull(commandFunc);
        this.map.replace(commandName, commandFunc);
    }
    
    private static void assertArgNotNull(final Object arg) {
        if (Registry.isNull(arg))
            throw new RuntimeException("Invalid argument");
    }

    private static boolean isNull(final Object arg) {
        return (arg == null) || 
            (arg instanceof String) && ("".equals(arg));
    }

    private void assertContainsName(final String commandName) {
        if (!this.contains(commandName))
            throw new RuntimeException("Command \"" + commandName + "\" didn't registered");
    }

    private void assertMapNotNull() {
        if (this.map == null)
            throw new RuntimeException("Invalid map");
    }
}

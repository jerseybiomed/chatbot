package chatbot;

import java.util.function.Consumer;

/**
 * Command
 */
public class Command
implements Runnable{
    private String name;
    private Consumer<String[]> func;
    private String[] args;

    @Override
    public void run() {
        this.assertArgsNotNull();
        this.func.accept(this.args);
        this.args = null;
    }

    private void assertArgsNotNull() {
        if (this.args == null || this.args.length == 0 || !this.args[0].equals(name))
            throw new RuntimeException("Invalid arguments");
    }

    public Command(final String m_name, final Consumer<String[]> m_func) {
        this.name = m_name;
        this.func = m_func;
    }

    public String getName() {
        return this.name;
    }

    public Command setArgs(final String[] m_args) {
        this.args = m_args;
        return this;
    }
}
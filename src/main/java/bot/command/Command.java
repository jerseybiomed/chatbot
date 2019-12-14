package bot.command;

import java.util.function.Consumer;

/**
 * Command
 */
public class Command
implements Runnable{
    private Consumer<String[]> func;
    private String[] args;

    @Override
    public void run() {
        this.assertArgsNotNull();
        this.func.accept(this.args);
        this.args = null;
    }

    private void assertArgsNotNull() {
        if (this.args == null || this.args.length == 0)
            throw new RuntimeException("Invalid arguments");
    }

    public Command(final Consumer<String[]> m_func) {
        this.func = m_func;
    }

    public Command setArgs(final String[] m_args) {
        this.args = m_args;
        return this;
    }
}
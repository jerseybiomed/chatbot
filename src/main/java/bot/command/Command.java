package bot.command;

import java.util.function.Consumer;

/**
 * Command
 */
public class Command<T>
implements Runnable{
    private Consumer<T> func;
    private T args;

    @Override
    public void run() {
        this.assertArgsNotNull();
        this.func.accept(this.args);
        this.args = null;
    }

    private void assertArgsNotNull() {
        if (this.args == null)
            throw new RuntimeException("Invalid arguments");
    }

    public Command(final Consumer<T> m_func) {
        this.func = m_func;
    }

    public Command<T> setArgs(final T m_args) {
        this.args = m_args;
        return this;
    }
}
package chatbot;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.Arrays;

public enum ECommands {
    Help("help", (args) -> System.out.println(Arrays.toString(args))),
    Balance("balance", (args) -> System.out.println(args)),
    Roll("roll", (args) -> System.out.println(args));

    public String name;
    public Consumer<String[]> func;

    private ECommands(final String m_name, final Consumer<String[]> m_func) {
        this.name = m_name;
        this.func = m_func;
    }

    public void sendTo(final BiConsumer<String, Consumer<String[]>> someApiFunction) {
        someApiFunction.accept(this.name, this.func);
    }

    public void sendTo(final BiConsumer<String, Consumer<String[]>> someApiFunction, Consumer<String[]> m_func) {
        someApiFunction.accept(this.name, m_func);
    }
}
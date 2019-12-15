package bot.banditbot;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import bot.command.Command;

public enum ECommands {
    Help("help", (args) -> System.out.println(args[0])),
    Say("say", (args) -> System.out.println(args[1]));

    private String name;
    private Command<String[]> comm;

    private ECommands(final String m_name, final Consumer<String[]> m_func) {
        this.name = m_name;
        this.comm = new Command<String[]>(m_func);
    }

    public void sendTo(final BiConsumer<String, Command<String[]>> someApiFunction) {
        someApiFunction.accept(this.name, this.comm);
    }

    public void sendTo(final BiConsumer<String, Command<String[]>> someApiFunction, final Consumer<String[]> m_func) {
        ECommands.assertFuncNotNull(m_func);
        someApiFunction.accept(this.name, new Command<String[]>(m_func));
    }

    private static void assertFuncNotNull(final Consumer<String[]> m_func) {
        if (m_func == null)
            throw new RuntimeException("Invalid function");
    }
}
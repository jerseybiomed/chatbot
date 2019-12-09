package bot;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public enum ECommands {
    Help("/help", (args) -> System.out.println(String.join(" ", args))),
    Balance("/balance", (args) -> System.out.println(String.join(" ", args))),
    Roll("/roll", (args) -> System.out.println(String.join(" ", args))),
    Bet("/bet", (args) -> System.out.println(String.join(" ", args))),
    Rules("/rules", (args) -> System.out.println(String.join(" ", args))),
    Back("/back", (args) -> System.out.println(String.join(" ", args))),
    Start("/start", (args) -> System.out.println(String.join(" ", args))),
    Bandit("/bandit", (args) -> System.out.println(String.join(" ", args))),
    Roulette("/roulette", (args) -> System.out.println(String.join(" ", args)));

    private String name;
    private Consumer<String[]> func;

    private ECommands(final String m_name, final Consumer<String[]> m_func) {
        this.name = m_name;
        this.func = m_func;
    }

    public void sendTo(final BiConsumer<String, Command> someApiFunction) {
        someApiFunction.accept(this.name, new Command(this.name, this.func));
    }

    public void sendTo(final BiConsumer<String, Command> someApiFunction, final Consumer<String[]> m_func) {
        ECommands.assertFuncNotNull(m_func);
        someApiFunction.accept(this.name, new Command(this.name, m_func));
    }

    private static void assertFuncNotNull(final Consumer<String[]> m_func) {
        if (m_func == null)
            throw new RuntimeException("Invalid function");
    }
}
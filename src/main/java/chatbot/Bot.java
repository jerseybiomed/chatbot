package chatbot;

import java.util.function.Consumer;

/**
 * Bot
 */
public class Bot {
    protected CommandRegistry<Consumer<String[]>> commands = new CommandRegistry<>();

    public Bot() {
        ECommands.Help.sendTo(commands::add);
        ECommands.Balance.sendTo(commands::add);
        ECommands.Roll.sendTo(commands::add);
    }

    public void perform(String msg) {
        String[] args = msg.split(" ");
        if (args.length > 0 && commands.contains(args[0]))
            commands.get(args[0]).accept(args);
    }
}
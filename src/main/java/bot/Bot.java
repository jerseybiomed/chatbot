package bot;

import logic.telegram.Player;
import logic.Publisher;
import logic.Subscriber;

/**
 * Bot
 */
public class Bot implements Subscriber<String> {
    protected CommandRegistry<Command> commands = new CommandRegistry<Command>();

    public Bot() {
        ECommands.Help.sendTo(commands::add, (args) -> System.out.println("It's very smart bot"));
        ECommands.Balance.sendTo(commands::add);
        ECommands.Roll.sendTo(commands::add);
    }

    public void perform(Player player, String[] args) {
        Bot.assertArgsNotNull(args);
        this.commands.get(args[0]).setArgs(args).run();
    }

    protected static void assertArgsNotNull(String[] args) {
        if (args == null || args.length == 0)
            throw new RuntimeException("Invalid arguments");
    }

    @Override
    public void listen(Publisher<String> from, Player player, String args) {
        this.perform(player, args.split(" "));
    }
}
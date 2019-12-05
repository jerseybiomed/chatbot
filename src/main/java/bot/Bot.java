package bot;

import bot.command.Command;
import bot.command.CommandRegistry;
import bot.command.ECommands;
import messenger.Connector.Connectable;
import messenger.Listener;

/**
 * Bot
 */
public class Bot extends Listener<String[]> {
    protected CommandRegistry<Command> commands = new CommandRegistry<Command>();

    public Bot() {
        ECommands.Help.sendTo(commands::add);
        ECommands.Roll.sendTo(commands::add);
        ECommands.Balance.sendTo(commands::add);
    }

    public void perform(final String[] args) {
        Bot.assertArgsNotNull(args);
        this.commands.get(args[0]).setArgs(args).run();
    }

    private static void assertArgsNotNull(final String[] args) {
        if (args == null || args.length == 0)
            throw new RuntimeException("Invalid arguments");
    }

    @Override
    public void connect(Connectable item) {
        this.start(item);
    }

    @Override
    protected void start(Object source) {
        this.perform(new String[] {"help"});
    }

    @Override
    public void listen(String[] arg) {
        this.perform(arg);
    }
}

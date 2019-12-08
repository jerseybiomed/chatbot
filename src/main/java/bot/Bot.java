package bot;

import bot.command.Command;
import bot.command.CommandRegistry;
import bot.command.ECommands;
import connection.Connectable;
import connection.messegestream.ConsoleStream;
import connection.messegestream.Listener;

/**
 * Bot
 */
public class Bot implements Listener<String[]>, Connectable<ConsoleStream> {
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
    public void connect(final ConsoleStream connection) {
        connection.addListener(new BotListener(this));
        this.start();
    }

    @Override
    public void listen(final String[] data) {
        this.perform(data);
    }

    @Override
    public void start() {
        this.perform(new String[]{"help"});
    }
}

package bot;

import bot.command.CommandRegistry;
import messagestream.Connection;
import messagestream.Listener;

/**
 * Bot
 */
public class Bot
implements Listener<String[]> {
    protected CommandRegistry commands = new CommandRegistry();

    public Bot() {
        ECommands.Help.sendTo(this.commands::add, (args) -> this.perform("_say", "help"));
        ECommands.Recognize.sendTo(this.commands::add, (args) -> this.perform("_say", "unknown command:", args[1]));
    }

    public void perform(final String... args) {
        Bot.assertArgsNotNull(args);
        if (this.commands.contains(args[0])) {
            this.commands.get(args[0]).setArgs(args).run();
        } else {
            this.perform("_recognize", args[0]);
        }
    }

    private static void assertArgsNotNull(final String[] args) {
        if (args.length == 0 || args[0] == null)
            throw new RuntimeException("Invalid arguments");
    }

    public void connect(final Connection<Listener<String>> connection) {
        connection.connect(new BotListener(this));
        this.start();
    }

    @Override
    public void listen(final String[] args) {
        if (args[0].charAt(0) == '_')
            return;
        this.perform(args);
    }

    @Override
    public void start() {
        this.perform("help");
    }
}

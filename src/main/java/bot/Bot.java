package bot;

import console.Messenger;

/**
 * Bot
 */
public class Bot {
    protected CommandRegistry<Command> commands = new CommandRegistry<Command>();
    protected Messenger messenger;

    public Bot(Messenger messenger) {
    	this.messenger = messenger; 
        ECommands.Help.sendTo(commands::add, (args) -> System.out.println("It's very smart bot"));
        ECommands.Balance.sendTo(commands::add);
        ECommands.Roll.sendTo(commands::add);
    }

    public void perform(final String[] args) {
        Bot.assertArgsNotNull(args);
        this.commands.get(args[0]).setArgs(args).run();
    }

    private static void assertArgsNotNull(String[] args) {
        if (args == null || args.length == 0)
            throw new RuntimeException("Invalid arguments");
    }
}
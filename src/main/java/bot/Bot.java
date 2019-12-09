package bot;

import console.Messenger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Bot
 */
public class Bot extends TelegramLongPollingBot {
    protected CommandRegistry<Command> commands = new CommandRegistry<Command>();
    protected String userName;
    protected String token;

    public Bot(String userName, String token) {
    	this.userName = userName;
    	this.token = token;
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

    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return token;
    }


}
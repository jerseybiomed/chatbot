package bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import bot.ChatBot;
import customer.TelegramCustomer;

/**
 * TelegramBot
 */
public class TelegramBot extends TelegramLongPollingBot {
    protected final ChatBot bot;

    public TelegramBot(ChatBot bot) {
        this.bot = bot;
    }

    @Override
    public void onUpdateReceived(Update update) {
        bot.perform(new bot.Request(new TelegramCustomer(update.getMessage().getChatId()), update.getMessage().getText()));
    }

    @Override
    public String getBotUsername() {
        return null;
    }

    @Override
    public String getBotToken() {
        return null;
    }
}
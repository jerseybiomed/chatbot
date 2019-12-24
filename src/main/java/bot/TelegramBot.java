package bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import bot.ChatBot;
import customer.TelegramCustomer;
import logic.Sender;
import logic.telegram.TelegramSender;

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
        if (update.getMessage().getText().equals("/start"))
            bot.register(new TelegramCustomer(update.getMessage().getChatId()), new TelegramSender(this, update.getMessage().getChatId()));
        else
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
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
    private String userName;
    private String token;

    public TelegramBot(ChatBot bot, String userName, String token) {
        this.bot = bot;
        this.userName = userName;
        this.token = token;
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
        return this.userName;
    }

    @Override
    public String getBotToken() {
        return this.token;
    }
}
package logic.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import logic.Sender;

/**
 * TelegramSender
 */
public class TelegramSender
implements Sender {
    private TelegramLongPollingBot bot;
    private long chatId;

    public TelegramSender(TelegramLongPollingBot bot, long chatId) {
        this.bot = bot;
        this.chatId = chatId;
    }

    @Override
    public void send(String message) {
        SendMessage msg = new SendMessage(chatId, message);
        try {
            bot.execute(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
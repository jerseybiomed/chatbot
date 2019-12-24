package logic.telegram;

import logic.Sender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * TelegramSender
 */
public class TelegramSender
implements Sender {
    private long chatId;

    public TelegramSender(long chatId) {
        this.chatId = chatId;
    }

    @Override
    public void send(String message) {}
}
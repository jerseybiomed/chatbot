package logic.telegram;

import logic.Sender;

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
    public void send(String message) {

    }
}
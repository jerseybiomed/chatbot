package logic;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import logic.Sender;

/**
 * TelegramSender
 */
public class TelegramSender
implements Sender<String> {
    private Sender<SendMessage> botSender;
    private long chatId;

    public TelegramSender(Sender<SendMessage> botSender, long chatId) {
        this.botSender = botSender;
        this.chatId = chatId;
    }

    @Override
    public void send(String message) {
        SendMessage msg = new SendMessage(chatId, message);
        botSender.send(msg);
    }
}
package bot;

import java.util.HashSet;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import logic.Sender;

/**
 * TelegramBot
 */
public class TelegramBot extends TelegramLongPollingBot implements IPublisher<MyMessage>, Sender<SendMessage> {
    private String userName;
    private String token;
    private HashSet<ISubscriber<MyMessage>> subs = new HashSet<>();

    public TelegramBot(String userName, String token) {
        this.userName = userName;
        this.token = token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        for (ISubscriber<MyMessage> sub : subs) {
            sub.listen(new MyMessage(update.getMessage().getText(), update.getMessage().getChatId()));
        }
    }

    @Override
    public String getBotUsername() {
        return this.userName;
    }

    @Override
    public String getBotToken() {
        return this.token;
    }

    @Override
    public void pub(ISubscriber<MyMessage> sub) {
        subs.add(sub);
    }

    @Override
    public void send(SendMessage message) {
        try {
            this.execute(message);   
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
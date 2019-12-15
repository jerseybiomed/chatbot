package messagestream.speakers;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import messagestream.Connection;
import messagestream.Listener;

/**
 * Telegram
 */
public class Telegram
extends TelegramLongPollingBot
implements Runnable, Speaker<String>, Connection<Listener<String>> {
    private final List<Listener<String>> listeners = new ArrayList<Listener<String>>();
    private SendMessage send = new SendMessage();
    
    @Override
    public void run() {
        ApiContextInitializer.init();
        try {
            new TelegramBotsApi().registerBot(this);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return null;
    }

    @Override
    public void onUpdateReceived(final Update update) {
        this.send.setChatId(update.getMessage().getChatId());
        String text = update.getMessage().getText();
        this.say(text);
    }

    @Override
    public String getBotToken() {
        return null;
    }

    @Override
    public void say(final String message) {
        for (final Listener<String> cl : this.listeners)
            cl.listen(this, message);
    }

    @Override
    public void reply(String answer) {
        try {
            this.execute(this.send.setText(answer));
        } catch (TelegramApiException  e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connect(final Listener<String> listener) {
        this.listeners.add(listener);
    }
}
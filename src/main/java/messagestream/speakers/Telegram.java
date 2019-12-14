package messagestream.speakers;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import messagestream.Connection;
import messagestream.Listener;

/**
 * Telegram
 */
public class Telegram
extends TelegramLongPollingBot
implements Runnable, Speaker<String>, Connection<Listener<String>> {
    private final List<Listener<String>> listeners = new ArrayList<Listener<String>>();
    
    @Override
    public void run() {

    }

    @Override
    public String getBotUsername() {
        return null;
    }

    @Override
    public void onUpdateReceived(final Update arg0) {

    }

    @Override
    public String getBotToken() {
        return null;
    }

    @Override
    public void say(final String message) {
        for (final Listener<String> cl : this.listeners)
            cl.listen(message);
    }

    @Override
    public void connect(final Listener<String> listener) {
        this.listeners.add(listener);
    }
}
package connection.messegestream;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Telegram
 */
public class Telegram extends TelegramLongPollingBot implements Runnable {
    

    @Override
    public void run() {

    }

    @Override
    public String getBotUsername() {
        return null;
    }

    @Override
    public void onUpdateReceived(Update arg0) {

    }

    @Override
    public String getBotToken() {
        return null;
    }
}
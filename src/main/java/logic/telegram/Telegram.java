package logic.telegram;

import game.UserId;
import logic.Connector;
import logic.Publisher;
import logic.Subscriber;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Telegram extends TelegramLongPollingBot
implements Publisher<String>, Connector<Subscriber<String>>, Subscriber<String> {
    private List<Subscriber<String>> subscribers = new ArrayList<>();
    private HashMap<Player, SendMessage> messages = new HashMap<>();
    private HashMap<Long, Player> players = new HashMap<>();
    private int numberPlayers = 0;
    private String userName;
    private String token;

    public Telegram(String userName, String token) {
        this.userName = userName;
        this.token = token;
    }

    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void publish(UserId id, String message) {
        for (Subscriber<String> subscriber : subscribers)
            subscriber.listen(id, message);
    }

    @Override
    public void connect(Subscriber<String> subscriber) {
        this.subscribers.add(subscriber);
    }

    private void setBanditKeyboard() {
        banditKeyboard.setSelective(true);
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("/help"));
        keyboardFirstRow.add(new KeyboardButton("/rules"));
        keyboardFirstRow.add(new KeyboardButton("/balance"));
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton("/roll 10"));
        keyboardSecondRow.add(new KeyboardButton("/roll 100"));
        keyboardSecondRow.add(new KeyboardButton("/roll 1000"));
        KeyboardRow keyboardThirdRow = new KeyboardRow();
        keyboardThirdRow.add(new KeyboardButton("/back"));
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThirdRow);
        banditKeyboard.setKeyboard(keyboard);
    }

    private void setRouletteKeyboard() {
        rouletteKeyboard.setSelective(true);
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("/help"));
        keyboardFirstRow.add(new KeyboardButton("/rules"));
        keyboardFirstRow.add(new KeyboardButton("/balance"));
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton("/bet red 100"));
        keyboardSecondRow.add(new KeyboardButton("/bet black 100"));
        keyboardSecondRow.add(new KeyboardButton("/bet 0 100"));
        KeyboardRow keyboardThirdRow = new KeyboardRow();
        keyboardThirdRow.add(new KeyboardButton("/back"));
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThirdRow);
        rouletteKeyboard.setKeyboard(keyboard);
    }

    private void setStartKeyboard() {
        startKeyboard.setSelective(true);
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(new KeyboardButton("/bandit"));
        keyboardRow.add(new KeyboardButton("/roulette"));
        keyboard.add(keyboardRow);
        startKeyboard.setKeyboard(keyboard);
    }

    @Override
    public void listen(UserId id, String data) {
        SendMessage sendMessage = new SendMessage();
        try {
            this.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

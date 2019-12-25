package logic.telegram;

import logic.Connector;
import logic.Publisher;
import logic.Subscriber;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Telegram extends TelegramLongPollingBot
implements Publisher<String>, Connector<Subscriber<String>> {
    private List<Subscriber<String>> subscribers = new ArrayList<>();
    private HashMap<Player, SendMessage> messages = new HashMap<>();
    private HashMap<Long, Player> players = new HashMap<>();
    private ReplyKeyboardMarkup banditKeyboard = new ReplyKeyboardMarkup();
    private ReplyKeyboardMarkup rouletteKeyboard = new ReplyKeyboardMarkup();
    private ReplyKeyboardMarkup startKeyboard = new ReplyKeyboardMarkup();
    private int numberPlayers = 0;
    private String userName;
    private String token;

    public Telegram(String name, String token) {
        userName = name;
        this.token = token;
        setBanditKeyboard();
        setRouletteKeyboard();
        setStartKeyboard();
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        Long chatId = message.getChatId();
        String text = message.getText();
        if (!players.containsKey(chatId)) {
            Player player = new Player();
            player.setPlayerNickname(numberPlayers++);
            players.put(chatId, player);
        }
        SendMessage sendMessage = new SendMessage().setChatId(chatId);
        sendMessage.setReplyMarkup(getKeyboard(players.get(chatId), text));
        messages.put(players.get(message.getChatId()), sendMessage);
        this.say(players.get(chatId), message.getText());
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
    public void say(Player player, String message) {
        for (Subscriber<String> subscriber : this.subscribers)
            subscriber.listen(this, player, message);
    }

    @Override
    public void reply(Player player, String answer) {
        SendMessage sendMessage = messages.get(player);
        sendMessage.setText(answer);
        sendMessage.setReplyMarkup(getKeyboard(player, ""));
        player.changeCurrentGame();
        try {
            this.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connect(Subscriber<String> subscriber) {
        this.subscribers.add(subscriber);
    }

    private ReplyKeyboardMarkup getKeyboard(Player player, String text) {
        if (text.equals("/start") || text.equals("/back")) {
            player.setNextGame("start");
        } else if (text.equals("/bandit")) {
            player.setNextGame("bandit");
        } else if (text.equals("/roulette")) {
            player.setNextGame("roulette");
        } else if (player.getCurrentGame().equals("roulette") && player.getRouletteBalance() <= 0) {
            player.setNextGame("start");
        } else if (player.getCurrentGame().equals("bandit") && player.getBanditBalance() <= 0) {
            player.setNextGame("start");
        }
        return player.getNextGame().equals("roulette") ? rouletteKeyboard
                : player.getNextGame().equals("bandit") ? banditKeyboard : startKeyboard;
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

}

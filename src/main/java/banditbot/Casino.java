package banditbot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bot.Bot;
import bot.ECommands;
import logic.Bandit;
import logic.Help;
import logic.RouletteDrum;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.AbstractMap.SimpleEntry;

/**
 * BotBandit
 */
public class Casino extends Bot {
    private String currentMenu;
    private Bandit bandit;
    private RouletteDrum rouletteDrum;
    private Message message;
    private HashMap<Long, Double> banditBalances = new HashMap<>();
    private HashMap<Long, Double> rouletteBalances = new HashMap<>();
    private ArrayList<Long> roulettePlayers = new ArrayList<>();
    private ReplyKeyboardMarkup banditKeyboard = new ReplyKeyboardMarkup();
    private ReplyKeyboardMarkup rouletteKeyboard = new ReplyKeyboardMarkup();
    private ReplyKeyboardMarkup startKeyboard = new ReplyKeyboardMarkup();

    public Casino(Bandit game1, RouletteDrum drum2, String userName, String token) {
        super(userName, token);
        currentMenu = "start";
        bandit = game1;
        rouletteDrum = drum2;
        setBanditKeyboard();
        setRouletteKeyboard();
        setStartKeyboard();
        ECommands.Roll.sendTo(this.commands::replace, this::banditRoll);
        ECommands.Balance.sendTo(this.commands::replace, (args) -> sendMessage(message, getBalance()));
        ECommands.Help.sendTo(this.commands::replace, (args) -> sendMessage(message, getHelp()));
        ECommands.Rules.sendTo(this.commands::add, (args) -> sendMessage(message, getRules()));
        ECommands.Bandit.sendTo(this.commands::add, (args) -> sendMessage(message, Help.banditHelp));
        ECommands.Roulette.sendTo(this.commands::add, (args) -> sendMessage(message, Help.rouletteHelp));
        ECommands.Back.sendTo(this.commands::add, (args) -> sendMessage(message, "Choose your game"));
        ECommands.Start.sendTo(this.commands::add, (args) -> sendMessage(message, "Choose your game"));
    }

    private String getRules() {
        return currentMenu.equals("bandit") ? Help.banditRules : Help.rouletteRules;
    }

    private String getHelp() {
        return currentMenu.equals("bandit") ? Help.banditHelp : Help.rouletteHelp;
    }

    private String getBalance() {
        return currentMenu.equals("bandit") ? banditBalances.get(message.getChatId()).toString()
                : rouletteBalances.get(message.getChatId()).toString();
    }

    private void banditRoll(String[] args) {
        int bet = Integer.parseInt(args[1]);
        if (bet <= banditBalances.get(message.getChatId())) {
            SimpleEntry<String, Double> result = bandit.game(bet);
            sendMessage(message, "line:" + result.getKey() + " result:" + result.getValue());
        } else if (banditBalances.get(message.getChatId()) < 1) {
            sendMessage(message, "You lost all the money\nTo start the game again with 10000 write '/start'\n" +
                    "Good Luck and Have Fun!");
        } else {
            sendMessage(message, "Your balance is not enough for this bet");
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            message = update.getMessage();
            try {
                String[] args = message.getText().split(" ");
                if (args[0].equals("/bandit")) {
                    banditBalances.put(message.getChatId(), 10000.0);
                    currentMenu = "bandit";
                }
                if (args[0].equals("/roulette")) {
                    if (roulettePlayers.size() < 1) {
                        roulettePlayers.add(message.getChatId());
                        rouletteBalances.put(message.getChatId(), 10000.0);
                        currentMenu = "roulette";
                    } else {
                        sendMessage(message, "There is no available space in roulette");
                    }
                }
                if (args[0].equals("/back")) {
                    if (currentMenu.equals("roulette")) {
                        roulettePlayers.remove(message.getChatId());
                    }
                    currentMenu = "start";
                }
                this.perform(args);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void sendMessage(Message message, String text) {
        SendMessage chat = new SendMessage();
        chat.setChatId(message.getChatId());
        chat.setText(text);
        if (currentMenu.equals("start"))
            chat.setReplyMarkup(startKeyboard);
        else if (currentMenu.equals("bandit"))
            chat.setReplyMarkup(banditKeyboard);
        else chat.setReplyMarkup(rouletteKeyboard);
        try{
            this.execute(chat);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
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
        keyboardSecondRow.add(new KeyboardButton("/back"));
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
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
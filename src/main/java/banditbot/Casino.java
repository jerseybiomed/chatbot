package banditbot;

import java.util.*;

import bot.Bot;
import bot.Command;
import bot.ECommands;
import logic.Bandit;
import logic.Help;
import logic.Roulette;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.AbstractMap.SimpleEntry;

/**
 * Casino
 */
public class Casino extends Bot {
    private String currentMenu;
    private Bandit bandit;
    private Roulette roulette;
    private Message message;
    private HashMap<Long, Double> banditBalances = new HashMap<>();
    private HashMap<Long, Double> rouletteBalances = new HashMap<>();
    private HashSet<Long> roulettePlayers = new HashSet<>();
    private HashMap<Long, String> rouletteBets = new HashMap<>();
    private ReplyKeyboardMarkup banditKeyboard = new ReplyKeyboardMarkup();
    private ReplyKeyboardMarkup rouletteKeyboard = new ReplyKeyboardMarkup();
    private ReplyKeyboardMarkup startKeyboard = new ReplyKeyboardMarkup();

    public Casino(Bandit game1, Roulette game2, String userName, String token) {
        super(userName, token);
        game2.setBot(this);
        currentMenu = "start";
        bandit = game1;
        roulette = game2;
        roulette.Start();
        setBanditKeyboard();
        setRouletteKeyboard();
        setStartKeyboard();
        ECommands.Roll.sendTo(this.commands::replace, this::banditRoll);
        ECommands.Balance.sendTo(this.commands::replace, (args) -> sendMessage(message.getChatId(), getBalance()));
        ECommands.Help.sendTo(this.commands::replace, (args) -> sendMessage(message.getChatId(), getHelp()));
        ECommands.Bet.sendTo(this.commands::add, (args) -> setRouletteBet(message));
        ECommands.Rules.sendTo(this.commands::add, (args) -> sendMessage(message.getChatId(), getRules()));
        ECommands.Bandit.sendTo(this.commands::add, (args) -> banditRequest());
        ECommands.Roulette.sendTo(this.commands::add, (args) -> rouletteRequest());
        ECommands.Back.sendTo(this.commands::add, (args) -> backRequest());
        ECommands.Start.sendTo(this.commands::add, (args) -> sendMessage(message.getChatId(), "Choose your game"));
        commands.add("sayResult", new Command("sayResult",
                (args) -> this.performRoulette(Integer.parseInt(args[1]))));
    }

    private void performRoulette(int result) {
        sendRoulettePlayers(result + roulette.getColor(result));
        for (long player : rouletteBets.keySet()) {
            String[] bet = rouletteBets.get(player).split(" ");
            double res = roulette.getCoefficient(result, bet[1]);
            rouletteBalances.replace(player, rouletteBalances.get(player) + res - Integer.parseInt(bet[2]));
            rouletteBets.remove(player);
            sendMessage(player, Double.toString(res));
        }
    }

    private void setRouletteBet(Message message) {
        String text = message.getText();
        rouletteBets.put(message.getChatId(), text);
        String[] bet = text.split(" ");
        sendRoulettePlayers("new bet: " + bet[2] + " on " + bet[1]);
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
            banditBalances.replace(message.getChatId(),
                    banditBalances.get(message.getChatId()) - bet + result.getValue());
            sendMessage(message.getChatId(), "line:" + result.getKey() + " result:" + result.getValue());
        } else if (banditBalances.get(message.getChatId()) < 1) {
            sendMessage(message.getChatId(), "You lost all the money\nTo start the game again with 10000 write '/start'\n"
                    + "Good Luck and Have Fun!");
        } else {
            sendMessage(message.getChatId(), "Your balance is not enough for this bet");
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            message = update.getMessage();
            try {
                String[] args = message.getText().split(" ");
                this.perform(args);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void banditRequest() {
        banditBalances.put(message.getChatId(), 10000.0);
        currentMenu = "bandit";
        sendMessage(message.getChatId(), Help.banditHelp);
    }

    private void rouletteRequest() {
        if (roulettePlayers.size() < 10 || roulettePlayers.contains(null)) {
            roulettePlayers.add(message.getChatId());
            rouletteBalances.put(message.getChatId(), 10000.0);
            for (long player : roulettePlayers)
                sendMessage(player, "Hello " + message.getChatId().toString());
            currentMenu = "roulette";
            sendMessage(message.getChatId(), Help.rouletteHelp);
        } else {
            sendMessage(message.getChatId(), "There is no available space in roulette");
            currentMenu = "start";
        }
    }

    private void backRequest() {
        if (currentMenu.equals("roulette")) {
            roulettePlayers.remove(message.getChatId());
        }
        currentMenu = "start";
        sendMessage(message.getChatId(), "Choose your game");
    }

    private void sendRoulettePlayers(String text) {
        for (long player : roulettePlayers)
            sendMessage(player, text);
    }

    private void sendMessage(long id, String text) {
        SendMessage chat = new SendMessage();
        chat.setChatId(id);
        chat.setText(text);
        if (currentMenu.equals("start"))
            chat.setReplyMarkup(startKeyboard);
        else if (currentMenu.equals("bandit"))
            chat.setReplyMarkup(banditKeyboard);
        else
            chat.setReplyMarkup(rouletteKeyboard);
        try {
            this.execute(chat);
        } catch (final TelegramApiException e) {
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
        keyboardSecondRow.add(new KeyboardButton("/bet red 100"));
        keyboardSecondRow.add(new KeyboardButton("/bet dark 100"));
        keyboardSecondRow.add(new KeyboardButton("/bet 0 100"));
        KeyboardRow keyboardThirdRow = new KeyboardRow();
        keyboardThirdRow.add(new KeyboardButton("/back"));
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

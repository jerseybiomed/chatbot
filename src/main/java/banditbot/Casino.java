package banditbot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bot.Bot;
import bot.Command;
import bot.ECommands;
import logic.Help;
import logic.Roulette;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * BotBandit
 */
public class Casino extends Bot {
    private String currentMenu;
    private final BanditDrum banditDrum;
    private Message message;
    private final HashMap<Long, Double> banditBalances = new HashMap<>();
    private final HashMap<Long, Double> rouletteBalances = new HashMap<>();
    private final ReplyKeyboardMarkup banditKeyboard = new ReplyKeyboardMarkup();
    private final ReplyKeyboardMarkup rouletteKeyboard = new ReplyKeyboardMarkup();
    private final ReplyKeyboardMarkup startKeyboard = new ReplyKeyboardMarkup();

    public Casino(final BanditDrum drum1, final Roulette game2, final String userName, final String token) {
        super(userName, token);
        currentMenu = "start";
        banditDrum = drum1;
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
        this.commands.add("roulette sayResult", new Command("roulette sayResult", (args) -> this.performRoulette(Integer.parseInt(args[2]))));
    }

    private void performRoulette(int x) {

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

    private void banditRoll(final String[] args) {
        final int bet = Integer.parseInt(args[1]);
        if (bet <= banditBalances.get(message.getChatId())) {
            final double res = banditDrum.roll(bet);
            banditBalances.replace(message.getChatId(), banditBalances.get(message.getChatId()) - bet + res);
            final String result = "line:" + banditDrum.getComb() + " result:" + res;
            sendMessage(message, result);
        } else if (banditBalances.get(message.getChatId()) < 1) {
            sendMessage(message, "You lost all the money\nTo start the game again with 10000 write '/start'\n"
                    + "Good Luck and Have Fun!");
        } else {
            sendMessage(message, "Your balance is not enough for this bet");
        }
    }

    @Override
    public void onUpdateReceived(final Update update) {
        if (update.hasMessage()) {
            message = update.getMessage();
            try {
                final String[] args = message.getText().split(" ");
                if (args[0].equals("/bandit")) {
                    banditBalances.put(message.getChatId(), 10000.0);
                    currentMenu = "bandit";
                }
                if (args[0].equals("/roulette")) {
                    rouletteBalances.put(message.getChatId(), 10000.0);
                    currentMenu = "roulette";
                }
                if (args[0].equals("/back")) {
                    currentMenu = "start";
                }
                this.perform(args);
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void sendMessage(final Message message, final String text) {
        final SendMessage chat = new SendMessage();
        chat.setChatId(message.getChatId());
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
        final List<KeyboardRow> keyboard = new ArrayList<>();
        final KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("/help"));
        keyboardFirstRow.add(new KeyboardButton("/rules"));
        keyboardFirstRow.add(new KeyboardButton("/balance"));
        final KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton("/roll 10"));
        keyboardSecondRow.add(new KeyboardButton("/roll 100"));
        keyboardSecondRow.add(new KeyboardButton("/roll 1000"));
        final KeyboardRow keyboardThirdRow = new KeyboardRow();
        keyboardThirdRow.add(new KeyboardButton("/back"));
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThirdRow);
        banditKeyboard.setKeyboard(keyboard);
    }

    private void setRouletteKeyboard() {
        rouletteKeyboard.setSelective(true);
        final List<KeyboardRow> keyboard = new ArrayList<>();
        final KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("/help"));
        keyboardFirstRow.add(new KeyboardButton("/rules"));
        keyboardFirstRow.add(new KeyboardButton("/balance"));
        final KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton("/back"));
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        rouletteKeyboard.setKeyboard(keyboard);
    }

    private void setStartKeyboard() {
        startKeyboard.setSelective(true);
        final List<KeyboardRow> keyboard = new ArrayList<>();
        final KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(new KeyboardButton("/bandit"));
        keyboardRow.add(new KeyboardButton("/roulette"));
        keyboard.add(keyboardRow);
        startKeyboard.setKeyboard(keyboard);
    }

}
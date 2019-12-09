package banditbot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bot.Bot;
import bot.ECommands;
import console.Messenger;
import logic.Help;
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
    private Drum drum;
    private Message message;
    private HashMap<Long, Double> balances = new HashMap<>();
    private ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    public Casino(Drum drum, String userName, String token) {
        super(userName, token);
        this.drum = drum;
        this.setReplyKeyboardMarkup();
        ECommands.Roll.sendTo(this.commands::replace, this::roll);
        ECommands.Balance.sendTo(this.commands::replace, (args) -> sendMessage(message, balances.get(message.getChatId()).toString()));
        ECommands.Help.sendTo(this.commands::replace, (args) -> sendMessage(message, Help.help));
        ECommands.Lines.sendTo(this.commands::add, (args) -> sendMessage(message, Help.lines));
        ECommands.Start.sendTo(this.commands::add, (args) -> sendMessage(message, Help.help));
    }

    private void roll(String[] args) {
        try {
            int bet = Integer.parseInt(args[1]);
            double res = drum.roll(bet);
            balances.replace(message.getChatId(), balances.get(message.getChatId()) - bet + res);
            String message = "line:" + drum.getComb() + " result:" + res;
            sendMessage(this.message, message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update){
        if (update.hasMessage()) {
            message = update.getMessage();
            try {
                String[] args = message.getText().split(" ");
                if (args[0].equals("/start"))
                    balances.put(message.getChatId(), 10000.0);
                this.perform(args);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void sendMessage(Message message, String text){
        SendMessage chat = new SendMessage();
        chat.setChatId(message.getChatId());
        chat.setText(text);
        chat.setReplyMarkup(replyKeyboardMarkup);
        try{
            this.execute(chat);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void setReplyKeyboardMarkup(){
        replyKeyboardMarkup.setSelective(true);
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("/help"));
        keyboardFirstRow.add(new KeyboardButton("/lines"));
        keyboardFirstRow.add(new KeyboardButton("/balance"));
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton("/roll 10"));
        keyboardSecondRow.add(new KeyboardButton("/roll 100"));
        keyboardSecondRow.add(new KeyboardButton("/roll 1000"));
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

}
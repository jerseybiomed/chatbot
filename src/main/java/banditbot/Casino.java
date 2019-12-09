package banditbot;

import java.io.IOException;
import java.util.ArrayList;
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
    private ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    public Casino(Drum drum, String userName, String token) {
        super(userName, token);
        this.drum = drum;
        this.setReplyKeyboardMarkup();
        ECommands.Roll.sendTo(this.commands::replace, this::roll);
        ECommands.Help.sendTo(this.commands::replace, (args) -> sendMessage(message, Help.help));
        ECommands.Lines.sendTo(this.commands::add, (args) -> sendMessage(message, Help.lines));
        ECommands.Start.sendTo(this.commands::add, (args) -> sendMessage(message, Help.help));
    }

    private void roll(String[] args) {
        try {
            double res = drum.roll(Integer.parseInt(args[1]));
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
                this.perform(message.getText().split(" "));
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
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton("/roll 100"));
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

}
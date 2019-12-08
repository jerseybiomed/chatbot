package banditbot;

import java.io.IOException;

import bot.Bot;
import bot.ECommands;
import console.Messenger;
import logic.Help;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * BotBandit
 */
public class OneArmBandit extends Bot {
    private Drum drum;
    private Message message;

    public OneArmBandit(Drum drum, String userName, String token) {
        super(userName, token);
        this.drum = drum;
        ECommands.Roll.sendTo(this.commands::replace, this::roll);
        ECommands.Help.sendTo(this.commands::replace, (args) -> sendMessage(message, Help.help));
        ECommands.Lines.sendTo(this.commands::add, (args) -> sendMessage(message, Help.lines));
        ECommands.Start.sendTo(this.commands::add, (args) -> sendMessage(message, Help.help));
    }

    private void roll(String[] args) {
        try {
            double res = drum.roll(Integer.parseInt(args[1]));
            sendMessage(this.message, drum.getComb());
            sendMessage(this.message, Double.toString(res));
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
        try{
            this.execute(chat);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
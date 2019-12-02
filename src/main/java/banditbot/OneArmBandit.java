package banditbot;

import java.io.IOException;

import bot.Bot;
import bot.ECommands;
import console.Messenger;

/**
 * BotBandit
 */
public class OneArmBandit extends Bot {
    private Drum drum = new Drum();

    public OneArmBandit(Messenger messenger) {
        super(messenger);
        ECommands.Roll.sendTo(this.commands::replace, this::roll);
    }

    private void roll(String[] args) {
        try {
            double res = drum.roll(Integer.parseInt(args[1]));
            messenger.write(drum.getComb());
            messenger.write(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
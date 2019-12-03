package banditbot;

import java.io.IOException;

import bot.Bot;
import bot.ECommands;
import console.Messenger;
import logic.Help;

/**
 * BotBandit
 */
public class OneArmBandit extends Bot {
    private Drum drum;

    public OneArmBandit(Messenger messenger, Drum drum) {
        super(messenger);
        this.drum = drum;
        ECommands.Roll.sendTo(this.commands::replace, this::roll);
        ECommands.Help.sendTo(this.commands::replace, (args) -> messenger.write(Help.help));
        this.perform("help".split(" "));
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
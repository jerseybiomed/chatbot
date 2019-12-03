package banditbot;

import java.io.IOException;

import bot.Bot;
import bot.ECommands;

/**
 * BotBandit
 */
public class OneArmBandit extends Bot {
    private Drum drum = new Drum();

    public OneArmBandit() {
        super();
        ECommands.Roll.sendTo(this.commands::replace, this::roll);
    }

    private void roll(String[] args) {
        try {
            System.out.println(drum.roll(Integer.parseInt(args[1])));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
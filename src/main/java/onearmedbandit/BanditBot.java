package onearmedbandit;

import java.io.IOException;

import bot.Bot;
import bot.ECommands;

/**
 * BotBandit
 */
public class BanditBot extends Bot {
    private Drum drum = new Drum();

    public BanditBot() {
        super();
        ECommands.Roll.sendTo(this.commands::replace, this::roll);
    }

    private void roll(final String[] args) {
        try {
            double res = drum.roll(Integer.parseInt(args[1]));
            System.out.println(drum.getComb());
            System.out.println(res);
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
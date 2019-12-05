package onearmedbandit;

import java.io.IOException;

import com.Help;

import bot.Bot;
import bot.command.ECommands;

/**
 * BotBandit
 */
public class BanditBot extends Bot {
    private final Drum drum = new Drum();

    public BanditBot() {
        super();
        ECommands.Help.sendTo(this.commands::replace, this::help);
        ECommands.Roll.sendTo(this.commands::replace, this::roll);
    }


    private void help(final String[] args) {
        System.out.println(Help.help);
    }

    private void roll(final String[] args) {
        try {
            final double res = drum.roll(Integer.parseInt(args[1]));
            System.out.println(drum.getComb());
            System.out.println(res);
        } catch (final NumberFormatException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
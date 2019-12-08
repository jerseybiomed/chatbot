package bot.banditbot;

import java.io.IOException;

import bot.Bot;
import bot.command.ECommands;

/**
 * BotBandit
 */
public class BanditBot extends Bot {
    private final Helper helper;
    private final Drum drum;

    public BanditBot(final logic.Help help, final logic.Calculator calculator) {
        super();
        this.helper = new Helper(help);
        this.drum = new Drum(calculator);
        ECommands.Help.sendTo(this.commands::replace, this::help);
        ECommands.Roll.sendTo(this.commands::replace, this::roll);
    }

    private void help(final String[] args) {
        this.helper.help();
    }

    private void roll(final String[] args) {
        try {
            System.out.println(drum.roll(Integer.parseInt(args[1])));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
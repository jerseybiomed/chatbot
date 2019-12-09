package com;

import bot.Bot;
import bot.banditbot.BanditBot;
import connection.messegestream.Console;
import logic.Calculator;
import logic.Help;

/**
 * BanditBot Launch
 */
public final class App {
    public static void main(final String[] args) {
        final Bot bot = new BanditBot(new Help(), new Calculator());
        final Console cons = new Console(System.in);
        bot.connect(cons);
        cons.run();
    }
}

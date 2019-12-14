package com;

import bot.Bot;
import messagestream.translator.Console;

/**
 * BanditBot Launch
 */
public final class App {
    public static void main(final String[] args) {
        final Console consOut = new Console();
        final Bot bot = new Bot(consOut);
        final messagestream.speakers.Console cons = new messagestream.speakers.Console();
        bot.connect(cons);
        cons.run();
    }
}

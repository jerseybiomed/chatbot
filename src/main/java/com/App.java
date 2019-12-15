package com;

import bot.Bot;
import bot.ChatBot;
import messagestream.speakers.ConsoleSpeaker;
import messagestream.translator.Console;

/**
 * BanditBot Launch
 */
public final class App {
    public static void main(final String[] args) {
        final Console consOut = new Console();
        final Bot bot = new ChatBot(consOut);
        final ConsoleSpeaker cons = new ConsoleSpeaker();
        bot.connect(cons);
        cons.run();
    }
}

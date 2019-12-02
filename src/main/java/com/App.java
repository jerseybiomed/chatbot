package com;

import java.util.Scanner;

import bot.Bot;
import bot.BotListener;
import messenger.Console;
import onearmedbandit.BanditBot;

/**
 * Hello world!
 */
public final class App {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Bot bot = new BanditBot();
        Console cons = new Console(System.in, System.out);
        cons.addListener(new BotListener(bot));
        cons.run();
    }
}

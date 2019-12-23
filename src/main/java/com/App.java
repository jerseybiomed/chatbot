package com;

import java.util.Scanner;

import bot.ChatBot;
import customer.ConsoleCustomer;
import games.bandit.BanditFabric;
import games.menu.MenuFabric;
import games.roulette.Roulette;
import games.roulette.RouletteFabric;
import logic.console.ConsoleSender;
import random.Randomize;

/**
 * App
 */
public final class App {

    public static void main(final String[] args) {
        /*
        EvGeni,
            look at the lines 30, 35 on class bot.TelegramReqestStream
            look at the line  18     on class logic.telegram.TelegramReqestStream
            and launch logic.telegram.TelegramReqestStream here
        I trust you this qest
        */
        ChatBot bot = new ChatBot(new MenuFabric(
            new BanditFabric(new Randomize()),
            new RouletteFabric(new Roulette(new Randomize()))));
        bot.register(new ConsoleCustomer("Pasha"), new ConsoleSender());
        Scanner cons = new Scanner(System.in);
        while (true) {
            bot.perform(new bot.Request(new ConsoleCustomer("Pasha"), cons.nextLine()));
        }
    }
}

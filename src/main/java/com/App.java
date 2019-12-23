package com;

import java.util.Scanner;

import bot.ChatBot;
import customer.ConsoleCustomer;
import customer.Customer;
import games.bandit.BanditFabric;
import games.menu.MenuFabric;
import games.roulette.Roulette;
import games.roulette.RouletteFabric;
import logic.console.ConsoleSender;
import random.Randomize;

/**
 * BanditBot Launch
 */
public final class App {

    public static void main(final String[] args) {
        /*
        EvGeni, look at the lines {} on class TelegramReqestStream
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

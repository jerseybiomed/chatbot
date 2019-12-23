package com;

import java.io.Console;

import bot.ChatBot;
import bot.ConsoleCustomer;
import bot.ConsoleSender;
import bot.Customer;
import bot.Menu;
import bot.MenuFabric;
import bot.MenuTaskCrafter;
import bot.SimpleGame;
import bot.SimpleGameFabric;

/**
 * BanditBot Launch
 */
public final class App {

    public static void main(final String[] args) {
        ChatBot bot = new ChatBot(new MenuFabric(new SimpleGameFabric()));
        Customer nia = new ConsoleCustomer("Pasha");
        bot.register(nia, new ConsoleSender());
        Console cons = System.console();
        while (true) {
            bot.perform(new bot.Request(nia, cons.readLine()));
        }
    }
}

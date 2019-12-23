package com;

import java.util.Scanner;

import bot.ChatBot;
import customer.ConsoleCustomer;
import logic.console.ConsoleSender;
import customer.Customer;
import games.menu.MenuFabric;
import games.simplegame.SimpleGameFabric;

/**
 * BanditBot Launch
 */
public final class App {

    public static void main(final String[] args) {
        ChatBot bot = new ChatBot(new MenuFabric(new SimpleGameFabric()));
        Customer player = new ConsoleCustomer("Pasha");
        bot.register(player, new ConsoleSender());
        Scanner cons = new Scanner(System.in);
        while (true) {
            bot.perform(new bot.Request(player, cons.nextLine()));
        }
    }
}

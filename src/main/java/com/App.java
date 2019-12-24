package com;

import java.util.Scanner;

import bot.ChatBot;
import bot.TelegramBot;
import customer.ConsoleCustomer;
import games.bandit.BanditFabric;
import games.menu.MenuFabric;
import games.roulette.Roulette;
import games.roulette.RouletteFabric;
import logic.console.ConsoleSender;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import random.Randomize;

/**
 * App
 */
public final class App {

    public static void main(final String[] args) {/*
        ChatBot bot = new ChatBot(new MenuFabric(
            new RouletteFabric(new Roulette(new Randomize()))
        ));
        bot.register(new ConsoleCustomer("Pasha"), new ConsoleSender());
        while (true) {
            bot.perform(new bot.Request(new ConsoleCustomer("Pasha"), System.console().readLine()));
        }*/
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        ChatBot bot = new ChatBot(new MenuFabric(
            new BanditFabric(new Randomize()),
            new RouletteFabric(new Roulette(new Randomize()))));
        TelegramBot telegram = new TelegramBot(bot
                , System.getenv("BOT_USERNAME"), System.getenv("BOT_TOKEN"));
        try {
            botsApi.registerBot(telegram);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}

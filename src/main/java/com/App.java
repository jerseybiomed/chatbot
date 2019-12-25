package com;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import bot.ChatBot;
import bot.TelegramBot;
import bot.TelegramDispatcher;
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
    private static void telegramLaunch() {
        ChatBot bot = new ChatBot(new MenuFabric(
            new BanditFabric(new Randomize()),
            new RouletteFabric(new Roulette(new Randomize()))));
        TelegramBot telegram = new TelegramBot(System.getenv("BOT_USERNAME"), System.getenv("BOT_TOKEN"));
        TelegramDispatcher dispatcher = new TelegramDispatcher(telegram, bot);
        telegram.pub(dispatcher);
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(telegram);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    private static void consoleLaunch() {
        ChatBot bot = new ChatBot(new MenuFabric(
            new BanditFabric(new Randomize()),
            new RouletteFabric(new Roulette(new Randomize()))));
        bot.register(new ConsoleCustomer("Valera"), new ConsoleSender());
        while (true) {
            bot.perform(new bot.Request(new ConsoleCustomer("Valera"), System.console().readLine()));
        }
    }
        
    public static void main(final String[] args) {
        telegramLaunch();
    }
}

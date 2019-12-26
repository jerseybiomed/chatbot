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
import logic.ConsoleSender;
import random.Randomize;

import java.util.Scanner;

/**
 * App
 */
public final class App {
    public static void telegramLaunch() {
        ApiContextInitializer.init();
        final TelegramBotsApi botsApi = new TelegramBotsApi();
        final ChatBot bot = new ChatBot(
                new MenuFabric(new BanditFabric(new Randomize()), new RouletteFabric(new Roulette(new Randomize()))));
        final TelegramBot telegram = new TelegramBot(System.getenv("BOT_USERNAME"), System.getenv("BOT_TOKEN"));
        final TelegramDispatcher dispatcher = new TelegramDispatcher(telegram, bot);
        telegram.pub(dispatcher);
        try {
            botsApi.registerBot(telegram);
        } catch (final TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    public static void consoleLaunch() {
        final ChatBot bot = new ChatBot(
                new MenuFabric(
            new BanditFabric(new Randomize()),
            new RouletteFabric(new Roulette(new Randomize()))));
        bot.register(new ConsoleCustomer("Valera"), new ConsoleSender());
        Scanner scanner = new Scanner(System.in);
        while (true) {
            bot.perform(new bot.Request(new ConsoleCustomer("Valera"), scanner.nextLine()));
        }
    }
        
    public static void main(final String[] args) {
        telegramLaunch();
    }
}

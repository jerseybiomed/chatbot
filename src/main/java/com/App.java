package com;

import logic.*;
import bot.Bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import web.Randomize;

/**
 * App
 */
public class App {

    public static void main(String[] args) {
        Randomize randomize = new Randomize();
        Bandit bandit = new Bandit(randomize);
        Roulette roulette = new Roulette(randomize);
    }
}

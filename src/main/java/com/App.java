package com;

import game.Bandit;
import game.Roulette;
import logic.dialog.Casino;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import web.Randomize;
import logic.telegram.Telegram;

/**
 * App
 */
public class App {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        Randomize randomize = new Randomize();
        Bandit bandit = new Bandit(randomize);
        Roulette roulette = new Roulette(randomize);
        Casino casino = new Casino(bandit, roulette);
        Telegram telegram = new Telegram();
        telegram.connect(casino);
        try {
            botsApi.registerBot(telegram);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}

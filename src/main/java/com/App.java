package com;

import banditbot.Casino;
import bot.Bot;
import logic.Bandit;
import logic.Roulette;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

/**
 * App
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        Bandit game1 = new Bandit();
        Roulette roulette = new Roulette();
        Bot bot = new Casino(game1, roulette, "Casino", "1050523384:AAHQXvGM4MB1eU1dVZ2vxfdO7bEzFFAk_lA");
        try {
            botsApi.registerBot(bot);
        } catch (TelegramApiRequestException e){
            e.printStackTrace();
        }
    }
}

package com;

import banditbot.Casino;
import logic.RouletteDrum;
import bot.Bot;
import logic.Bandit;
import logic.Calculator;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

/**
 * App
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        Roulette tt = new Roulette(null);
        tt.Start();
        while (true) {
            System.out.flush();
            System.out.println(tt.getTimeLeft());
            Thread.sleep(100);
        }
        /*ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        Calculator calculator = new Calculator();
        Bandit game1 = new Bandit();
        RouletteDrum drum2 = new RouletteDrum(calculator);
        Bot bot = new Casino(game1, drum2, "Casino", "1050523384:AAHQXvGM4MB1eU1dVZ2vxfdO7bEzFFAk_lA");
        try {
            botsApi.registerBot(bot);
        } catch (TelegramApiRequestException e){
            e.printStackTrace();
        }*/
    }
}

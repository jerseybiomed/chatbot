package com;

import logic.Roulette;

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
        BanditDrum drum1 = new BanditDrum(calculator);
        RouletteDrum drum2 = new RouletteDrum(calculator);
        Bot bot = new Casino(drum1, drum2, "Casino", "1050523384:AAHQXvGM4MB1eU1dVZ2vxfdO7bEzFFAk_lA");
        try {
            botsApi.registerBot(bot);
        } catch (TelegramApiRequestException e){
            e.printStackTrace();
        }*/
    }
}
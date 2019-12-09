package logic;

import banditbot.Drum;
import banditbot.Casino;
import bot.Bot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

/**
 * App
 */
public class App {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        Calculator calculator = new Calculator();
        Drum drum = new Drum(calculator);
        Bot bot = new Casino(drum, "Casino", "1050523384:AAHQXvGM4MB1eU1dVZ2vxfdO7bEzFFAk_lA");
        try {
            botsApi.registerBot(bot);
        } catch (TelegramApiRequestException e){
            e.printStackTrace();
        }
    }
}
package logic;

import banditbot.BanditDrum;
import banditbot.Casino;
import banditbot.RouletteDrum;
import bot.Bot;
import com.google.inject.internal.cglib.core.$LocalVariablesSorter;
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
        BanditDrum drum1 = new BanditDrum(calculator);
        RouletteDrum drum2 = new RouletteDrum(calculator);
        Bot bot = new Casino(drum1, drum2, "Casino", "1050523384:AAHQXvGM4MB1eU1dVZ2vxfdO7bEzFFAk_lA");
        try {
            botsApi.registerBot(bot);
        } catch (TelegramApiRequestException e){
            e.printStackTrace();
        }
    }
}
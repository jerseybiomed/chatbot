import banditbot.OneArmBandit;
import bot.Bot;
import console.Messenger;

/**
 * App
 */
public class App {

    public static void main(String[] args) {
        Bot bot = new OneArmBandit();
        Messenger messenger = new Messenger();
        while (true) {
            String[] comm_args = messenger.read();
            bot.perform(comm_args);
        }
    }
}
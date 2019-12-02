import banditbot.OneArmBandit;
import bot.Bot;
import console.Messenger;

/**
 * App
 */
public class App {

    public static void main(String[] args) {
        Messenger messenger = new Messenger();
        messenger.write(Help.help);
        Bot bot = new OneArmBandit(messenger);
        while (true) {
            String[] comm_args = messenger.read();
            bot.perform(comm_args);
        }
    }
}
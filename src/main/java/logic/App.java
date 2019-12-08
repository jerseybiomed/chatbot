package logic;

import banditbot.Drum;
import banditbot.OneArmBandit;
import bot.Bot;
import console.Messenger;
import logic.Calculator;
import logic.Help;

/**
 * App
 */
public class App {

    public static void main(String[] args) {
        Messenger messenger = new Messenger();
        Calculator calculator = new Calculator();
        Drum drum = new Drum(calculator);
        Bot bot = new OneArmBandit(messenger, drum);
        while (true) {
            String[] comm_args = messenger.read();
            bot.perform(comm_args);
        }
    }
}
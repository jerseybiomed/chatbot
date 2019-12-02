import java.util.Scanner;

import chatbot.Bot;
import onearmedbandit.BotBandit;

/**
 * App
 */
public class App {

    public static void main(String[] args) {
        Bot bot = new BotBandit();
        Scanner scanner = new Scanner(System.in);
        boolean temp = true;
        while (temp) {
            final String[] comm_args = scanner.nextLine().split(" ");
            bot.perform(comm_args);
        }
        scanner.close();
    }
}
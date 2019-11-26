import java.util.Scanner;

import chatbot.Bot;

/**
 * App
 */
public class App {

    public static void main(String[] args) {
        Bot bot = new Bot();
        Scanner scanner = new Scanner(System.in);
        boolean temp = true;
        while (temp)
            bot.perform(scanner.nextLine());
        scanner.close();
    }
}
package com;

import java.util.Scanner;

import bot.Bot;
import onearmedbandit.BotBandit;

/**
 * Hello world!
 */
public final class App {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Bot bot = new BotBandit();
        while (true) {
            String[] bot_args = sc.nextLine().split(" ");
            bot.perform(bot_args);
        }
    }
}

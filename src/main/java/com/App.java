package com;

import java.util.Scanner;

import bot.Bot;
import onearmedbandit.BanditBot;

/**
 * Hello world!
 */
public final class App {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Bot bot = new BanditBot();
        while (true) {
            String[] bot_args = sc.nextLine().split(" ");
            bot.perform(bot_args);
        }
    }
}

package com;

import game.Bandit;
import game.Roulette;
import logic.dialog.Casino;
import web.Randomize;
import logic.telegram.Telegram;

/**
 * App
 */
public class App {

    public static void main(String[] args) {
        Randomize randomize = new Randomize();
        Bandit bandit = new Bandit(randomize);
        Roulette roulette = new Roulette(randomize);
        Casino casino = new Casino(bandit, roulette);
        Telegram telegram = new Telegram();
        telegram.connect(casino);
        telegram.run();
    }
}

package logic.dialog;

import bot.Bot;
import game.Roulette;
import logic.telegram.Player;

import java.net.Inet4Address;
import java.util.HashMap;
import java.util.HashSet;

public class RouletteLogic {
    private Bot bot;
    private HashSet<Player> players;
    private HashMap<Player, String[]> bets;

    public void setBot(Bot bot) {
        this.bot = bot;
    }

    public String rouletteRequest(Player player, Roulette roulette) {
        players = roulette.getPlayers();
        if (players.size() < 10) {
            roulette.addPlayer(player);
            players = roulette.getPlayers();
            for (Player gamer : players)
                bot.perform(gamer, new String[]{"attention", "Hello " + player.getPlayerNickname() +
                        "\nAvailable seats: " + (10 - players.size())});
            return Roulette.Help;
        }
        return "Not enough seats";
    }

    public String checkResult(Player player, String result, Roulette roulette) {
        int res = Integer.parseInt(result);
        result += " " + roulette.getColor(res).toUpperCase();
        bets = roulette.getBets();
        if (bets.containsKey(player)) {
            String[] bet = bets.get(player);
            int betResult = roulette.betResult(player, bet, res);
            result +=  "\nYou win: " + betResult + "\n Current balance: " + player.getRouletteBalance();
        }
        if (player.getRouletteBalance() <= 0) {
            roulette.removePlayer(player);
            result += "\nYou lost all money\nThe guards kicked you out\nGood Luck and Have Fun!";
        }
        return result;
    }

    public String backRequest(Player player, Roulette roulette) {
        players = roulette.getPlayers();
        roulette.removePlayer(player);
        for (Player gamer : players)
            bot.perform(gamer, new String[]{"attention", "Goodbye " + player.getPlayerNickname() +
                    "\nAvailable seats: " + (10 - players.size())});
        return "Choose your game";
    }

    public void betRequest(Player player, String[] args, Roulette roulette) {
        players = roulette.getPlayers();
        if (Integer.parseInt(args[2]) <= player.getRouletteBalance()) {
            roulette.bet(player, new String[]{args[1], args[2]});
            for (Player gamer : players)
                bot.perform(gamer, new String[]{"attention", player.getPlayerNickname() + " bet " + args[2] +
                        " on " + args[1].toUpperCase()});
        } else {
            bot.perform(player, new String[]{"attention", "Not enough balance\nCurrent balance: " +
                    player.getRouletteBalance()});
        }
    }

    public String helpRequest() {
        return Roulette.Help;
    }

    public String rulesRequest() {
        return Roulette.Rules;
    }
}

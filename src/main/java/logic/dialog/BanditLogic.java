package logic.dialog;

import game.Bandit;
import logic.telegram.Player;

import java.util.AbstractMap.SimpleEntry;

public class BanditLogic {

    public String banditRequest(Player player) {
        if (player.getBanditBalance() <= 0)
            player.setBanditBalance(10000);
        return Bandit.Help;
    }

    public String rollRequest(Player player, String bet, Bandit bandit) {
        if (Integer.parseInt(bet) <= player.getBanditBalance()) {
            SimpleEntry<String, Integer> result = bandit.roll(player, Integer.parseInt(bet));
            if (player.getBanditBalance() <= 0)
                return "Current line: " + result.getKey() + " result: " + result.getValue() +
                        "\nCurrent balance: " + player.getBanditBalance() +
                        "\nYou lost all money\nThe guards kicked you out\nGood Luck and Have Fun!";
            return "Current line: " + result.getKey() + " result: " + result.getValue() +
                    "\nCurrent balance: " + player.getBanditBalance();
        }
        return "Not enough balance\nCurrent balance: " + player.getBanditBalance();
    }

    public String backRequest() {
        return "Choose your game";
    }

    public String helpRequest() {
        return Bandit.Help;
    }

    public String rulesRequest() {
        return Bandit.Rules;
    }
}

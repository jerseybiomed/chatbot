package games.roulette;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import customer.CustomerState;
import games.GameClient;
import task.RouletteTaskCreator;
import task.TaskCreator;

public class RouletteClient extends GameClient {
    private Timer timer = new Timer(true);
    private boolean isL = false;
    private Roulette roulette;
    private int balance = 0;
    private int currentBet = 0;
    private String choice;
    private int result = 0;
    private List<Integer> red = Arrays.asList(32, 19, 21, 25, 34, 27, 36, 30, 23, 5, 16, 1, 14, 9, 18, 7, 12, 3);
    private List<Integer> black = Arrays.asList(15, 4, 2, 17, 6, 13, 11, 8, 10, 24, 33, 20, 31, 22, 29, 28, 35, 26);
    private String help =
            "You are greeted by a RouletteClient\n" +
                    "To play enter '/red' or '/black' and the bet amount separated by a space\n" +
                    "Every 30 seconds there is a new scrolling roulette\n" +
                    "After scrolling you can bet on the following by entering '/bet red' or '/bet black' " +
                    "or '/bet NUMBER'" +
                    "and the bet amount separated by a space (NUMBER between 0 and 36)\n" +
                    "You can see the winning payment by enter '/rules'\n" +
                    "Also all participants reported when someone put somewhere put a bet\n" +
                    "\nFor output of this help once again instead of roll enter '/help'";
    private String rules =
            "If you bet on the color and guess, then your bet increases by 2\n" +
                    "If you bet on the number and guess, the bet increases by 36\n" +
                    "If you do not guess, you lose money";

    public RouletteClient(GameClient from, CustomerState exState, Roulette roulette) {
        super(from, exState);
        this.balance = 10000;
        this.roulette = roulette;
    }

    @Override
    public TaskCreator getTaskCreator() {
        return new RouletteTaskCreator();
    }

    @Override
    public String getHelp() {
        return this.help;
    }

    public String getRules() {
        return this.rules;
    }

    public boolean isLaunched() {
        return isL;
    }

    public void launch(TimerTask task) {
        timer.scheduleAtFixedRate(task, 10 * 1000, 10 * 1000);
        isL = true;
    }

    public void leave() {
        timer.cancel();
    }

    public int roll() {
        result = this.roulette.getNext();
        if (choice != null) {
            int change = getCoefficient() * currentBet;
            balance += change;
            choice = null;
            currentBet = 0;
            return change;
        }
        return -1;
    }

    public int getCoefficient() {
        if (choice.equals(Integer.toString(result)))
            return 36;
        else if (choice.equals(getColor(result)))
            return 2;
        return 0;
    }

    public String getColor(int result) {
        return red.contains(result) ? "red" : black.contains(result) ? "black" : "green";
    }

    public void bet(int bet, String choice) {
        this.choice = choice;
        this.balance -= bet;
        this.currentBet = bet;
    }

    public int getBalance() {
        return this.balance;
    }

    public int getBet() {
        return this.currentBet;
    }

    public int getResult() {
        return result;
    }

    public static String getName() {
        return "roulette";
    }
}

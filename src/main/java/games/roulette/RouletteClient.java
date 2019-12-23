package games.roulette;

import customer.CustomerState;
import games.GameClient;
import task.TaskCreator;

import java.util.Arrays;
import java.util.List;

public class RouletteClient extends GameClient {
    private Roulette roulette;
    private int balance = 0;
    private int currentBet = 0;
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

    public RouletteClient(GameClient m_from, CustomerState m_exState, Roulette roulette) {
        super(m_from, m_exState);
        this.balance = 10000;
        this.roulette = roulette;
    }

    @Override
    public TaskCreator<GameClient> getTaskCreator() {
        return null;
    }

    @Override
    public String getHelp() {
        return this.help;
    }

    public String getRules() {
        return this.rules;
    }

    public int getCoefficient(int result, String bet) {
        if (bet.equals(Integer.toString(result)))
            return 36;
        else if (bet.equals(getColor(result)))
            return 2;
        return 0;
    }

    public String getColor(int result) {
        return red.contains(result) ? "red" : black.contains(result) ? "black" : "green";
    }

    public void bet(int bet) {
        this.balance -= bet;
        this.currentBet = bet;
    }

    public int getBalance() {
        return this.balance;
    }

    public int getBet() {
        return this.currentBet;
    }
}

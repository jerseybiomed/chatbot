package games.bandit;

import customer.CustomerState;
import games.GameClient;
import random.Randomize;
import task.BanditTaskCreator;
import task.TaskCreator;

import java.io.IOException;

public class BanditClient
extends GameClient {
    private Randomize randomize;
    private int balance = 0;
    private int currentBet = 0;
    private String currentCombination = "000";
    private String help =
            "You are greeted by a One Arm BanditClient\n" +
                    "To play enter '/bet' and the bet amount separated by a space\n" +
                    "I will output the final line and the result\n" +
                    "You can see the winning lines by enter '/rules'\n" +
                    "I'm meant for gamblers, so they don't lose their money\n" +
                    "\nFor output of this help once again instead of roll enter '/help'";
    private String rules =
            "The first class of lines are lines with three identical cells\n" +
                    "Such lines increase bets by 2\n" +
                    "The second class of lines are lines with two identical cells\n" +
                    "Such lines increase bets by 1.5\n" +
                    "The third class of lines are 'beautiful' lines\n" +
                    "These lines have the same difference between adjacent cells\n" +
                    "Such lines keep the bet\n" +
                    "The other lines tell you of defeat";

    public BanditClient(final GameClient from, final CustomerState exState, Randomize randomize) {
        super(from, exState);
        this.balance = 10000;
        this.randomize = randomize;
    }

    public BanditClient(final CustomerState exState, Randomize randomize) {
        super(exState);
        this.balance = 10000;
        this.randomize = randomize;
    }

    @Override
    public TaskCreator getTaskCreator() {
        return new BanditTaskCreator();
    }

    @Override
    public String getHelp() {
        return this.help;
    }
    
    public String getRules() {
    	return this.rules;
    }

    public int roll() {
        String request = "https://www.random.org/integers/?num=10&min=1&max=50&col=1&base=10&format=plain&rnd=new";
        String combination = "";
        for (int i=0; i < 3; i++) {
            try {
                int random = randomize.Next(request);
                combination += (Integer.parseInt(currentCombination.substring(i, i + 1)) + random) % 10;
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        this.currentCombination = combination;
        int result = getCoefficient() * this.currentBet;
        this.balance += result;
        currentBet = 0;
        return result;
    }

    public int getCoefficient() {
        Combinator combinator = new Combinator();
        if (combinator.isFirstClassWinnerComb(currentCombination))
            return 3;
        else if (combinator.isSecondClassWinnerComb(currentCombination))
            return 2;
        else if (combinator.isBeautifulComb(currentCombination))
            return 1;
        return 0;
    }

    public String getCombination() {
        return currentCombination;
    }

    public int getBet() {
        return currentBet;
    }

    public void bet(int bet) {
        currentBet += bet;
        this.balance -= bet;
    }

    public static String getName() {
        return "bandit";
    }
}

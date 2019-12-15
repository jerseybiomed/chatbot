package logic;

import web.Randomize;

import java.util.AbstractMap.SimpleEntry;

public class Bandit {
    private BanditDrum drum;
    public static String Help =
            "You are greeted by a One Arm Bandit\n" +
                    "To play enter '/roll' and the bet amount separated by a space\n" +
                    "I will output the final line and the result\n" +
                    "You can see the winning lines by enter '/rules'\n" +
                    "I'm meant for gamblers, so they don't lose their money\n" +
                    "\nFor output of this help once again instead of roll enter '/help'";
    public static String Rules =
            "The first class of lines are lines with three identical cells\n" +
                    "Such lines increase bets by 2\n" +
                    "The second class of lines are lines with two identical cells\n" +
                    "Such lines increase bets by 1.5\n" +
                    "The third class of lines are 'beautiful' lines\n" +
                    "These lines have the same difference between adjacent cells\n" +
                    "Such lines keep the bet\n" +
                    "The other lines tell you of defeat";

    public Bandit(Randomize randomize) {
        this.drum = new BanditDrum(randomize);
    }

    public SimpleEntry<String, Double> roll(int bet) {
        drum.nextCombination();
        return new SimpleEntry<>(drum.getCombination(), bet * drum.getCoefficient());
    }

}

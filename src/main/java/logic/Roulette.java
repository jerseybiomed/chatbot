package logic;

import java.io.IOException;
import java.util.*;

import bot.Bot;
import web.Randomize;

/**
 * Roulette
 */
public class Roulette extends TimerTask {
    private Bot bot;
    private Timer timer;
    private Randomize randomize;
    private List<Integer> red = Arrays.asList(32, 19, 21, 25, 34, 27, 36, 30, 23, 5, 16, 1, 14, 9, 18, 7, 12, 3);
    private List<Integer> black = Arrays.asList(15, 4, 2, 17, 6, 13, 11, 8, 10, 24, 33, 20, 31, 22, 29, 28, 35, 26);
    public static String Help =
            "You are greeted by a Roulette\n" +
                    "To play enter '/red' or '/black' and the bet amount separated by a space\n" +
                    "Every 30 seconds there is a new scrolling roulette\n" +
                    "After scrolling you can bet on the following by entering '/bet red' or '/bet black' or '/bet NUMBER'" +
                    "and the bet amount separated by a space (NUMBER between 0 and 36)\n" +
                    "You can see the winning payment by enter '/rules'\n" +
                    "Also all participants reported when someone put somewhere put a bet\n" +
                    "\nFor output of this help once again instead of roll enter '/help'";
    public static String Rules =
            "If you bet on the color and guess, then your bet increases by 2\n" +
                    "If you bet on the number and guess, the bet increases by 36\n" +
                    "If you do not guess, you lose money";

    public Roulette(Randomize randomize) {
        this.randomize = randomize;
        this.timer = new Timer(true);
    }

    public void setBot(Bot m_bot) {
        this.bot = m_bot;
    }

    @Override
    public void run() {
        String request = "https://www.random.org/integers/?num=10&min=0&max=36&col=1&base=10&format=plain&rnd=new";
        try {
            this.bot.perform(new String[] {"sayResult", Integer.toString(randomize.Next(request))});
        } catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void Start() {
        this.timer.scheduleAtFixedRate(this, 30 * 1000, 30 * 1000);
    }

    public long getTimeLeft() {
        return new Date().getTime() - this.scheduledExecutionTime();
    }

    public double getCoefficient(int result, String bet) {
        if (bet.equals(Integer.toString(result)))
                return 36.0;
         else if (bet.equals(getColor(result)))
             return 2.0;
         return 0;
    }

    public String getColor(int result) {
        return red.contains(result) ? "red" : black.contains(result) ? "black" : "green";
    }
}
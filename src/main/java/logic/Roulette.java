package logic;

import java.io.IOException;
import java.util.*;

import bot.Bot;
import web.Connector;

import static jdk.nashorn.internal.runtime.JSType.isNumber;

/**
 * Roulette
 */
public class Roulette extends TimerTask {
    private Bot bot;
    private Timer timer;
    private List<Integer> red = Arrays.asList(32, 19, 21, 25, 34, 27, 36, 30, 23, 5, 16, 1, 14, 9, 18, 7, 12, 3);
    private List<Integer> black = Arrays.asList(15, 4, 2, 17, 6, 13, 11, 8, 10, 24, 33, 20, 31, 22, 29, 28, 35, 26);

    public Roulette() {
        this.timer = new Timer(true);
    }

    public void setBot(Bot m_bot) {
        this.bot = m_bot;
    }

    @Override
    public void run() {
        String request = "https://www.random.org/integers/?num=10&min=0&max=36&col=1&base=10&format=plain&rnd=new";
        int random = 0;
        try {
            random = Connector.GetRandomNumber(request);
            this.bot.perform(new String[] {"sayResult", Integer.toString(random)});
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

    public double getCofficient(int result, String bet) {
        if (isNumber(bet))
            if (Integer.parseInt(bet) == result)
                return 36.0;
         else if (bet.equals(getColor(result)))
             return 2.0;
         return 0;
    }

    public String getColor(int result) {
        return red.contains(result) ? " red" : black.contains(result) ? " black" : " green";
    }
}
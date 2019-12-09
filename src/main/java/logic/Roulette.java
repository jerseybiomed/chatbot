package logic;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import bot.Bot;
import web.Connector;

/**
 * roulet
 */
public class Roulette extends TimerTask {
    private final Bot bot;
    private final Timer timer;

    public Roulette(final Bot m_bot) {
        this.bot = m_bot;
        this.timer = new Timer(true);
    }

    @Override
    public void run() {
        final String request = "https://www.random.org/integers/?num=10&min=0&max=36&col=1&base=10&format=plain&rnd=new";
        int random = 0;
        try {
            random = Connector.GetRandomNumber(request);
        } catch (final IOException e) {
			e.printStackTrace();
		}
        this.bot.perform(new String[] {"roulette sayResult", Integer.toString(random)});
    }

    public void Start() {
        this.timer.scheduleAtFixedRate(this, 30 * 1000, 30 * 1000);
    }

    public long getTimeLeft() {
        return new Date().getTime() - this.scheduledExecutionTime();
    }
}
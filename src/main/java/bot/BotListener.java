package bot;

import com.Listener;

/**
 * BotListener
 */
public class BotListener implements Listener<String> {
    private Bot bot;

    public BotListener(final Bot m_bot) {
        this.bot = m_bot;
    }

    @Override
    public void listen(final String line) {
        // TODO Auto-generated method stub
        bot.listen(line.split(" "));
    }
}
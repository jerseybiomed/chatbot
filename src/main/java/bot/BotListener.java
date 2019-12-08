package bot;

import connection.messegestream.Listener;

/**
 * BotListener
 */
public class BotListener implements Listener<String>{
    private final Bot bot;

    public BotListener(final Bot m_bot) {
        this.bot = m_bot;
    }

    @Override
    public void listen(final String data) {
        final String[] args = data.split(" ");
        this.bot.perform(args);
    }

    @Override
    public void start() {
        this.bot.start();
    }
}
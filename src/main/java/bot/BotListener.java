package bot;

import messenger.Connector.Connectable;
import messenger.Listener;

/**
 * BotListener
 */
public class BotListener extends Listener<String> {
    private Bot bot;

    public BotListener(final Bot m_bot) {
        this.bot = m_bot;
    }

    @Override
    public void connect(Connectable item) {
        this.bot.connect(item);
    }

    @Override
    public void start(Object source) {
        this.bot.start(source);
    }

    @Override
    public void listen(final String line) {
        bot.listen(line.split(" "));
    }
}
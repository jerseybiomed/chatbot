package bot;

import messagestream.Listener;
import messagestream.speakers.Speaker;

/**
 * BotListener
 */
public class BotListener
implements Listener<String> {
    private final Bot bot;

    public BotListener(final Bot m_bot) {
        this.bot = m_bot;
    }

    @Override
    public void listen(Speaker<String> from, String data) {
        String[] args = data.split(" ");
        if (args[0].charAt(0) == '/') {
            args[0] = args[0].substring(1);
            this.bot.listen(from, args);
        }
    }
}
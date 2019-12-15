package messagestream.translator;

/**
 * Telegram
 */
public class Telegram
extends Translator<String> {
    private messagestream.speakers.TelegramSpeaker telegram;

    public Telegram(messagestream.speakers.TelegramSpeaker m_telegram) {
        this.telegram = m_telegram;
    }

    @Override
    public void listen(String message) {
        this.say(message);
    }

    @Override
    public void start() {}

    @Override
    public void say(String message) {
        this.telegram.sendMessage(message);
    }
}
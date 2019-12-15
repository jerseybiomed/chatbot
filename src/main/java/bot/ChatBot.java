package bot;

import java.util.Arrays;

import messagestream.speakers.Speaker;

/**
 * ChatBot
 */
public class ChatBot
extends Bot {
    private Speaker<String> answerer;

    public ChatBot(Speaker<String> m_answerer) {
        super();
        this.answerer = m_answerer;
        ECommands.Say.sendTo(this.commands::add, this::say);
    }

    @Override
    public void listen(String[] args) {
        this.answerer = null;
        super.listen(args);
    }

    private void say(String... args) {
        this.answerer.say(String.join(" ", Arrays.copyOfRange(args, 1, args.length)));
    }

    public ChatBot setAnswerer(Speaker<String> m_answerer) {
        this.answerer = m_answerer;
        return this;
    }
}
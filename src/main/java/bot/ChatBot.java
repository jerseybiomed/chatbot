package bot;

import java.util.Arrays;

import messagestream.speakers.Speaker;

/**
 * ChatBot
 */
public class ChatBot
extends Bot {
    private Speaker<String> speaker;

    public ChatBot(Speaker<String> m_speaker) {
        super();
        this.speaker = m_speaker;
        ECommands.Say.sendTo(this.commands::add, (args) -> this.speaker.say(String.join(" ", Arrays.copyOfRange(args, 1, args.length))));
    }    
}
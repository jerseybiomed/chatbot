package bot;

import java.util.HashSet;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import customer.TelegramCustomer;
import logic.Sender;
import logic.TelegramSender;

/**
 * TelegramDispatcher
 */
public class TelegramDispatcher implements ISubscriber<MyMessage> {
    private final HashSet<TelegramCustomer> users = new HashSet<>();
    private final ChatBot bot;
    private final Sender<SendMessage> tgSender;

    public TelegramDispatcher(Sender<SendMessage> tgSender, ChatBot bot) {
        this.tgSender = tgSender;
        this.bot = bot;
    }

    @Override
    public void listen(MyMessage msg) {
        TelegramCustomer user = new TelegramCustomer(msg.customer);
        if (!users.contains(user)){
            bot.register(user, new TelegramSender(tgSender, msg.customer));
            users.add(user);
        }
        bot.perform(new bot.Request(user, msg.message));
    }
}
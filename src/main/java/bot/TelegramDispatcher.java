package bot;

import java.util.HashSet;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import customer.Customer;
import customer.TelegramCustomer;
import logic.Sender;
import logic.telegram.TelegramSender;

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
        Customer user = new TelegramCustomer(msg.customer);
        if (!users.contains(user)){
            System.out.println(msg.customer);
            bot.register(user, new TelegramSender(tgSender, msg.customer));
        }
        bot.perform(new bot.Request(user, msg.message));
    }
}
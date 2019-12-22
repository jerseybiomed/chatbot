package bot;

import java.util.HashMap;

/**
 * ChatBot
 */
public class ChatBot {
    protected final HashMap<Customer, CustomerData> customerBasa = new HashMap<>();
    private final Game menu;

    public ChatBot(final Game m_menu, final Game... games) {
        this.menu = m_menu;
    }

    public void register(Customer customer, Sender replySender) {
        this.customerBasa.put(customer, new CustomerData(menu, replySender));
    }

    public void perform(final Request request) {
        Customer customer = request.customer;
        CustomerData data = this.customerBasa.get(customer);
        CustomerState state = data.state;
        TaskCrafter crafter = state.crafter;
        Game game = state.game;
        Sender reply = data.replySender;
        String[] args = request.message.split(" ");
        Task task = crafter.craft(args);
        task.perform(game, reply);
    }
}
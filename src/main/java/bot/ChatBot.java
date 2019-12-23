package bot;

import java.util.HashMap;

/**
 * ChatBot
 */
public class ChatBot {
    protected final HashMap<Customer, CustomerData> customerBase = new HashMap<>();
    private final MenuFabric menu;

    public ChatBot(final MenuFabric m_menu) {
        this.menu = m_menu;
    }

    public void register(Customer customer, Sender replySender) {
        CustomerData data = new CustomerData(replySender);
        this.customerBase.put(customer, data);
        CustomerState exState = data.state;
        exState.setGame(this.menu.newGame(exState));
    }

    public void perform(final Request request) {
        Customer customer = request.customer;
        CustomerData data = this.customerBase.get(customer);
        CustomerState state = data.state;
        TaskCrafter crafter = state.crafter;
        Game game = state.game;
        Sender reply = data.replySender;
        String[] args = request.message.split(" ");
        Task task = crafter.craft(args);
        task.perform(game, reply);
    }
}
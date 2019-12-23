package bot;

import java.util.HashMap;

/**
 * ChatBot
 */
public class ChatBot {
    protected final HashMap<Customer, CustomerData> customerBasa = new HashMap<>();
    private final MenuFabric menu;

    public ChatBot(final MenuFabric m_menu) {
        this.menu = m_menu;
    }

    public void register(Customer customer, Sender replySender) {
        this.customerBasa.put(customer, new CustomerData(replySender));
        CustomerState exState = this.customerBasa.get(customer).state;
        exState.setGame(this.menu.newGame(exState));
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
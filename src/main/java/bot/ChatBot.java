package bot;

import java.util.HashMap;

/**
 * ChatBot
 */
public class ChatBot {
    protected final HashMap<Customer, CustomerData> customerBasa = new HashMap<>();

    public ChatBot(final TaskCrafter m_crafter) {
    }

    public void perform(Request request) {
        Customer customer = request.customer;
        CustomerData data = this.customerBasa.get(customer);
        CustomerState state = data.state;
        Task task = state.crafter.craft(request.message, state);
    }
}
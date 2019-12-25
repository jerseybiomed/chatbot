package bot;

import customer.Customer;
import customer.CustomerData;
import customer.CustomerState;
import games.GameClient;
import games.menu.MenuFabric;
import logic.Sender;
import task.Task;
import task.TaskCreator;

import java.util.HashMap;

/**
 * ChatBot
 */
public class ChatBot {
    private final HashMap<Customer, CustomerData> customerBase = new HashMap<>();
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
        TaskCreator<GameClient> creator = state.getCreator();
        GameClient gameClient = state.getGame();
        Sender reply = data.replySender;
        String[] args = request.message.split(" ");
        if (args.length == 0 || args[0].charAt(0) != '/')
            return;
        args[0] = args[0].substring(1);
        Task<GameClient> task = creator.create(args);
        task.perform(gameClient, reply);
    }
}
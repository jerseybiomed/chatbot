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

    public void register(Customer customer, Sender<String> replySender) {
        CustomerData data = new CustomerData(customer, 10000, replySender);
        data.getState().focus = menu.newGame(data.getState());
        this.customerBase.put(customer, data);
    }

    public void perform(final Request request) {
        String[] args = request.message.split(" ");
        if (args.length == 0 || args[0].charAt(0) != '/')
            return;
        args[0] = args[0].substring(1);
        Customer customer = request.customer;
        CustomerData data = this.customerBase.get(customer);
        Sender<String> replySender = data.getReplySender();
        if (args[0].equals("start")) {
            register(customer, replySender);
        }
        CustomerState state = data.getState();
        TaskCreator<GameClient> creator = data.getTaskCreator(state.focus.getGameName());
        Task<GameClient> task = creator.create(args);
        GameClient game = state.focus;
        task.perform(game, replySender);
    }
}
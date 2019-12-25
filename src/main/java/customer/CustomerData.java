package customer;

import java.util.HashMap;

import games.GameClient;
import logic.Sender;
import task.DefaultTaskCreator;
import task.TaskCreator;

/**
 * CustomerData
 */
public class CustomerData {
    private final CustomerState state;
    private final HashMap<String, TaskCreator<GameClient>> creators = new HashMap<>();
    private final Sender<String> replySender;

    public CustomerData(final Customer customer, final int initialBalance, final Sender<String> m_replySender) {
        state = new CustomerState(customer, initialBalance);
        this.replySender = m_replySender;
    }

    public CustomerState getState() {
        return state;
    }

    public TaskCreator<GameClient> getTaskCreator(final String gameName) {
        if (!creators.containsKey(gameName))
            creators.put(gameName, DefaultTaskCreator.getDefaultTaskCreator(gameName));
        return creators.get(gameName);
    }

    public Sender<String> getReplySender() {
        return replySender;
    }
}
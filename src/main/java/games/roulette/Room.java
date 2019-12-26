package games.roulette;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import customer.Customer;

/**
 * Room
 */
public class Room {
    private final ConcurrentHashMap<Customer, CustomerDataForRoom> customersData = new ConcurrentHashMap<>();

    public boolean join(final Customer customer, final CustomerDataForRoom data) {
        if (customersData.size() >= 10)
            return false;
        newCustomer(customer);
        customersData.put(customer, data);
        return true;
    }

    public void leave(final Customer customer) {
        customersData.remove(customer);
    }

    public void newCustomer(final Customer customer) {
        for (final Map.Entry<Customer, CustomerDataForRoom> entry : customersData.entrySet()) {
            entry.getValue().onNewCustomer.accept(customer);
        }
    }

    public void newBet(final Customer customer, final String bet) {
        for (final Map.Entry<Customer, CustomerDataForRoom> entry : customersData.entrySet()) {
            entry.getValue().onNewBet.accept(customer, bet);
        }
    }

    public void sayResult(final Integer result) {
        for (final Map.Entry<Customer, CustomerDataForRoom> entry : customersData.entrySet()) {
            entry.getValue().onResult.accept(result);
        }
    }
}
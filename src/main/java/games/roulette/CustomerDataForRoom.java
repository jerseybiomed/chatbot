package games.roulette;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import customer.Customer;

/**
 * CustomerDataForRoom
 */
public class CustomerDataForRoom {
    public final Consumer<Customer> onNewCustomer;
    public final BiConsumer<Customer, String> onNewBet;
    public final Consumer<Integer> onResult;

    public CustomerDataForRoom(final Consumer<Customer> onNewCustomer, final BiConsumer<Customer, String> onBet,
            final Consumer<Integer> onRoll) {
        this.onNewCustomer = onNewCustomer;
        this.onNewBet = onBet;
        this.onResult = onRoll;
    }
}
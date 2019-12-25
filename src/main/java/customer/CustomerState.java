package customer;

import games.GameClient;

/**
 * CustomerState
 */
public class CustomerState {
    public final Customer customer;
    public GameClient focus;
    public int balance;

    public CustomerState(final Customer customer, final int balance) {
        this.customer = customer;
        this.balance = balance;
    }
}

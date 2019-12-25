package customer;

/**
 * TelegramCustomer
 */
public class TelegramCustomer
extends Customer {
    private final Long id;

    public TelegramCustomer(final Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String asString() {
        return Long.toString(id);
    }
}
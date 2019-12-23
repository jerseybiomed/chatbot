package customer;

/**
 * ConsoleCustomer
 */
public class ConsoleCustomer
extends Customer {
    public final String name;

    public ConsoleCustomer(final String m_name) {
        this.name = m_name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
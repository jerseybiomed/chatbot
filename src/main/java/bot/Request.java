package bot;

import customer.Customer;

/**
 * Request
 */
public class Request {
    public final Customer customer;
    public final String message;

    public Request(Customer m_customer, String m_message) {
        this.customer = m_customer;
        this.message = m_message;
    }
}
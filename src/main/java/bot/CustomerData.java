package bot;

/**
 * CustomerData
 */
public class CustomerData {

    public final CustomerState state;
    public final Sender replySender;

    public CustomerData(final Sender m_replySender) {
        this.state = new CustomerState();
        this.replySender = m_replySender;
    }
}
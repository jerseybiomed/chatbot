package bot;

/**
 * CustomerData
 */
public class CustomerData {
    public final CustomerState state = new CustomerState();
    public final Sender replySender;

    public CustomerData(final Sender m_replySender) {
        this.replySender = m_replySender;
    }
}
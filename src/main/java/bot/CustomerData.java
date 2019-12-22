package bot;

/**
 * CustomerData
 */
public class CustomerData {
    public final CustomerState state;
    public final Sender replySender;

    public CustomerData(final Game game, final Sender m_replySender) {
        this.state = new CustomerState(game);
        this.replySender = m_replySender;
    }
}
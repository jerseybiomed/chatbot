package bot;

/**
 * CustomerData
 */
public class CustomerData {
    public final CustomerState state = new CustomerState(new TaskCrafter());
    public final Publisher replyPublisher;

    public CustomerData(final Publisher m_replyPublisher) {
        this.replyPublisher = m_replyPublisher;
    }
}
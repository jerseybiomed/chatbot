package bot;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import customer.ConsoleCustomer;
import games.menu.MenuFabric;
import logic.Sender;

/**
 * TestBot
 */
public class TestBot {
    public String catch_;

    /**
     * TestSender
     */
    public class TestSender implements Sender<String> {
        TestBot test;

        public TestSender(TestBot test) {
            this.test = test;
        }

        @Override
        public void send(String message) {
            test.catch_ = message;
        }
    }

    @Test
    void testLaunch() {
        ChatBot bot = new ChatBot(new MenuFabric());
        bot.register(new ConsoleCustomer("Pasha"), new TestSender(this));
        catch_ = null;
        bot.perform(new bot.Request(new ConsoleCustomer("Pasha"), "/help"));
        assertNotNull(catch_);
    }
}
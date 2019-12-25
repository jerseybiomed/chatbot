package customer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * TestCustomer
 */
public class TestCustomer {

    @Test
    void testHash() {
        assertEquals(new ConsoleCustomer("test"), new ConsoleCustomer("test"));
        assertEquals(new TelegramCustomer(101l), new TelegramCustomer(101l));
    }
}
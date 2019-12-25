package games.roulette;

import java.util.Timer;
import java.util.TimerTask;

import customer.Customer;
import random.Randomize;

public class Roulette {
    private final Room room = new Room();
    private final Randomize randomize;
    private final String request = "https://www.random.org/integers/?num=10&min=0&max=36&col=1&base=10&format=plain&rnd=new";

    /**
     * Roll
     */
    public class Roll extends TimerTask {

        @Override
        public void run() {
            room.sayResult(next());
        }
    }

    public Roulette(Randomize randomize) {
        new Timer(true).scheduleAtFixedRate(new Roll(), 30 * 1000, 30 * 1000);
        this.randomize = randomize;
    }

    public int next() {
        try {
            return this.randomize.Next(this.request);    
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void leave(final Customer customer) {
        room.leave(customer);
    }

    public void join(Customer customer, CustomerDataForRoom data) {
        room.join(customer, data);
    }

	public void newBet(Customer customer, String bet) {
        room.newBet(customer, bet);
	}
}

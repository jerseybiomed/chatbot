package games.roulette;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import customer.Customer;
import customer.CustomerState;
import games.GameClient;

public class RouletteClient extends GameClient {
    private Roulette roulette;
    private int bet = 0;
    private String choice = "";
    private int result;
    private List<Integer> red = Arrays.asList(32, 19, 21, 25, 34, 27, 36, 30, 23, 5, 16, 1, 14, 9, 18, 7, 12, 3);
    private List<Integer> black = Arrays.asList(15, 4, 2, 17, 6, 13, 11, 8, 10, 24, 33, 20, 31, 22, 29, 28, 35, 26);
    private String help = "You are greeted by a RouletteClient\n"
            + "To play enter '/red' or '/black' and the bet amount separated by a space\n"
            + "Every 30 seconds there is a new scrolling roulette\n"
            + "After scrolling you can bet on the following by entering '/bet red' or '/bet black' "
            + "or '/bet NUMBER'" + "and the bet amount separated by a space (NUMBER between 0 and 36)\n"
            + "You can see the winning payment by enter '/rules'\n"
            + "Also all participants reported when someone put somewhere put a bet\n"
            + "\nFor output of this help once again instead of roll enter '/help'";
    private String rules = "If you bet on the color and guess, then your bet increases by 2\n"
            + "If you bet on the number and guess, the bet increases by 36\n" + "If you do not guess, you lose money";

    public RouletteClient(GameClient from, CustomerState exState, Roulette roulette) {
        super(from, exState);
        this.roulette = roulette;
    }

    public RouletteClient(CustomerState exState, Roulette roulette) {
        super(exState);
        this.roulette = roulette;
    }

    @Override
    public String getGameName() {
        return getName();
    }

    @Override
    public String getHelp() {
        return help;
    }

    @Override
    public int getBalance() {
        return state.exState.balance;
    }

    public static String getName() {
        return "roulette";
    }

    public String getRules() {
        return rules;
    }

    public void sayResult(int result) {
        this.result = result;
    }

    public int getCoefficient() {
        if (choice.equals(Integer.toString(result)))
            return 36;
        else if (choice.equals(getColor(result)))
            return 2;
        return 0;
    }

    public String getColor(int result) {
        return red.contains(result) ? "red" : black.contains(result) ? "black" : "green";
    }

    public void leave() {
        roulette.leave(state.exState.customer);
    }

    public void bet(int bet, String choice) {
        if (choice.equals(this.choice)) {
            state.exState.balance -= bet;
            this.bet += bet;
        } else {
            state.exState.balance += this.bet - bet;
            this.choice = choice;
            this.bet = bet;
        }
        roulette.newBet(state.exState.customer, choice);
    }

    public int getBet() {
        return bet;
    }

    public int getWin() {
        if (result == -1)
            return 0;
        int win = bet * getCoefficient();
        state.exState.balance += win;
        bet = 0;
        result = -1;
        return win;
    }

    public void join(Consumer<Customer> onNewCustomer, BiConsumer<Customer, String> onNewBet,
            Consumer<Integer> onResult) {
        roulette.join(state.exState.customer, new CustomerDataForRoom(onNewCustomer, onNewBet, onResult));
    }
}

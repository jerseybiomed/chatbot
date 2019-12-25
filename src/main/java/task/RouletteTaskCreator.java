package task;

import customer.Customer;
import games.roulette.RouletteClient;
import logic.Sender;

/**
 * RouletteTaskCreator
 */
public class RouletteTaskCreator
extends TaskCreator<RouletteClient> {

    protected class JoinTask
    extends Task<RouletteClient> {

        private void onNewCustomer(final Customer customer, final Sender<String> replySender) {
            replySender.send("Join new customer:\n----" + customer.asString());
        }

        private void onNewBet(final Customer customer, final String bet,
                              final RouletteClient game, final Sender<String> replySender) {
            replySender.send("New bet:\n----" + customer.asString() + "\n----On " + bet);
        }

        private void onResult(final Integer result, final RouletteClient game, final Sender<String> replySender) {
            game.sayResult(result);
            replySender.send("----" + result + " " + game.getColor(result) + "----\n" +
                             "You won: " + Integer.toString(game.getWin()));
        }

        @Override
        public void perform(final RouletteClient game, final Sender<String> replySender) {
            game.join((c) -> onNewCustomer(c, replySender), (c, b) -> onNewBet(c, b, game, replySender),
                      (r) -> onResult(r, game, replySender));
        }
    }

    protected class BetTask extends Task<RouletteClient> {
        private final int bet;
        private final String choice;

        public BetTask(final int bet, final String choice) {
            this.bet = bet;
            this.choice = choice;
        }

        @Override
        public void perform(final RouletteClient game, final Sender<String> replySender) {
            game.bet(bet, choice);
        }
    }

    protected class RuleTask extends Task<RouletteClient> {

        @Override
        public void perform(final RouletteClient game, final Sender<String> replySender) {
            final String rules = game.getRules();
            replySender.send(rules);
        }
    }

    protected class LeaveTask extends Task<RouletteClient> {

        @Override
        public void perform(final RouletteClient game, final Sender<String> replySender) {
            game.leave();
        }
    }

    protected class BackTask extends Task<RouletteClient> {

        @Override
        public void perform(final RouletteClient game, final Sender<String> replySender) {
            game.leave();
            game.back();
        }
    }

    @Override
    public Task create(final String[] args) {
        switch (args[0]) {
            case "back":
                return new BackTask();
            case "leave":
                return new LeaveTask();
            case "bet":
                if (args.length < 2)
                    return new EmptyTask();
                return new BetTask(Integer.parseInt(args[1]), args[2]);
            case "join":
                return new JoinTask();
            case "rule":
                return new RuleTask();
        }
        return new DefaultTaskCreator().create(args);
    }
}
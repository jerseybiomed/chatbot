package task;

import games.bandit.BanditClient;
import logic.Sender;

/**
 * BanditTaskCreator
 */
public class BanditTaskCreator extends TaskCreator<BanditClient> {

    protected class RollTask extends Task<BanditClient> {
        private int bet;

        public RollTask(int bet) {
            this.bet = bet;
        }

        @Override
        public void perform(BanditClient game, Sender<String> replySender) {
            if (game.bet(bet)) {
                int res = game.roll();
                replySender.send("Current combination: " + game.getCombination() + "\n" +
                        "Bet: " + bet + "\n" +
                        "Coefficient: X" + game.getCoefficient() + "\n" +
                        "Result: " + res);
            } else if (bet <= 0) {
                replySender.send("You can't bet <= 0");
            } else {
                replySender.send("Not enough balance");
            }
        }
    }

    protected class RuleTask
    extends Task<BanditClient> {

        @Override
        public void perform(BanditClient game, Sender<String> replySender) {
            replySender.send(game.getRules());
        }
    }

    @Override
    public Task create(String[] args) {
        switch (args[0]) {
            case "roll":
                if (args.length < 1)
                    return new EmptyTask();
                return new RollTask(Integer.parseInt(args[1]));
            case "rule":
                return new RuleTask();
        }
        return new DefaultTaskCreator().create(args);
    }
}
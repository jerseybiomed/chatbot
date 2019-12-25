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
            game.bet(bet);
            int res = game.roll();
            replySender.send("-" + game.getCombination() + "-\n" +
                             "|" + Integer.toString(bet) + "\n" +
                             "--X" + Integer.toString(game.getCoefficient()) + "-\n" +
                             "|" + Integer.toString(res));
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
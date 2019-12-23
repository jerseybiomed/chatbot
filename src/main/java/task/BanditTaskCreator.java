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
        public void perform(BanditClient game, Sender replySender) {
            game.bet(bet);
            int res = game.roll();
            replySender.send("-" + game.getCombination() + "-");
            replySender.send("|" + Integer.toString(bet));
            replySender.send("--X" + Integer.toString(game.getCoefficient()) + "-");
            replySender.send("|" + Integer.toString(res));
        }
    }

    protected class RuleTask
    extends Task<BanditClient> {

        @Override
        public void perform(BanditClient game, Sender replySender) {
            replySender.send(game.getRules());
        }
    }

    @Override
    public Task create(String[] args) {
        switch (args[0]) {
            case "roll":
                return new RollTask(Integer.parseInt(args[1]));
            case "rule":
                return new RuleTask();
        }
        return new DefaultTaskCreator().create(args);
    }
}
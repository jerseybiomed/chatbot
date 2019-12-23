package task;

import java.util.TimerTask;

import games.roulette.RouletteClient;
import logic.Sender;

/**
 * RouletteTaskCreator
 */
public class RouletteTaskCreator
extends TaskCreator<RouletteClient> {

    protected class LaunchTask
    extends Task<RouletteClient> {

        protected class RouletteTask
        extends TimerTask {
            private RouletteClient game;
            private Sender reply;

            public RouletteTask(RouletteClient game, Sender replySender) {
                this.game = game;
                this.reply = replySender;
            }

            @Override
            public void run() {
                int win = game.roll();
                reply.send(Integer.toString(game.getResult()) + " " + game.getColor(game.getResult()));
                if (win != -1) {
                    reply.send("You won " + Integer.toString(win));
                }
            }
        }

        @Override
        public void perform(RouletteClient game, Sender replySender) {
            game.launch(new RouletteTask(game, replySender));
        }
    }

    protected class BetTask
    extends Task<RouletteClient> {
        private int bet;
        private String choice;

        public BetTask(int bet, String choice) {
            this.bet = bet;
            this.choice = choice;
        }

        @Override
        public void perform(RouletteClient game, Sender replySender) {
            if (game.isLaunched()) {
                game.bet(bet, choice);
                replySender.send("Bet " + Integer.toString(bet) + " on " + choice);
            }
        }
    }

    protected class RuleTask
    extends Task<RouletteClient> {

        @Override
        public void perform(RouletteClient game, Sender replySender) {
            replySender.send(game.getRules());
        }
    }

    protected class BackTask
    extends Task<RouletteClient> {

        @Override
        public void perform(RouletteClient game, Sender replySender) {
            game.leave();
            game.back();
        }
    }

    @Override
    public Task create(String[] args) {
        switch (args[0]) {
            case "back":
                return new BackTask();
            case "launch":
                return new LaunchTask();
            case "bet":
                return new BetTask(Integer.parseInt(args[1]), args[2]);
            case "rule":
                return new RuleTask();
        }
        return new DefaultTaskCreator().create(args);
    }
}
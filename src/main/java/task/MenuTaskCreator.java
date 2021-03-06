package task;

import games.menu.Menu;
import logic.Sender;

/**
 * MenuTaskCreator
 */
public class MenuTaskCreator
extends TaskCreator<Menu> {
    protected static class ChooseTask
    extends Task<Menu> {
        private final String choice;

        public ChooseTask(final String m_choice) {
            this.choice = m_choice;
        }

        @Override
        public void perform(Menu menu, Sender<String> sender) {
            menu.choose(choice);
            sender.send("You choose " + menu.getGameName() + " game, please enter /help to take instructions");
        }
    }

    @Override
    public Task create(final String[] args) {
        switch (args[0]) {
            case "start":
                args[0] = "help";
                break;
            case "choose":
                if (args.length < 1)
                    return new EmptyTask();
                return new ChooseTask(args[1]);
        }
        return new DefaultTaskCreator().create(args);
    }
}
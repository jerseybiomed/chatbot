package bot;

/**
 * MenuTaskCrafter
 */
public class MenuTaskCrafter
extends TaskCrafter<Menu> {
    protected static class ChooseTask
    extends Task<Menu> {
        private final String choice;

        public ChooseTask(final String m_choice) {
            this.choice = m_choice;
        }

        @Override
        public void perform(Menu menu, Sender sender) {
            menu.choose(this.choice);
        }
    }

    @Override
    public Task craft(final String[] args) {
        switch (args[0]) {
            case "choose":
                return new ChooseTask(args[1]);
        }
        return new DefaultTaskCrafter().craft(args);
    }
}
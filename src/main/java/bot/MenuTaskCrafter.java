package bot;

/**
 * MenuTaskCrafter
 */
public class MenuTaskCrafter
extends TaskCrafter {


    @Override
    public Task craft(final String[] args) {
        switch (args[0]) {
            default:
                break;
        }
        return new DefaultTaskCrafter().craft(args);
    }
}
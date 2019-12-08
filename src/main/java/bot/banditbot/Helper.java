package bot.banditbot;

/**
 * Helper
 */
public class Helper {
    private logic.Help help;

    public Helper(logic.Help m_help) {
        this.help = m_help;
    }

    public void help() {
        System.out.println(this.help.getHelp());
    }
}
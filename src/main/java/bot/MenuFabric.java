package bot;

/**
 * MenuFabric
 */
public class MenuFabric
extends GameFabric<Menu> {
    private GameFabric[] fabrics;

    public MenuFabric(GameFabric... m_fabrics) {
        this.fabrics = m_fabrics;
    }

    @Override
    public Menu newGame(CustomerState state) {
        return new Menu(new MenuTaskCrafter(), state, this.fabrics);
    }

    @Override
    public String getGameName() {
        return "menu";
    }
}
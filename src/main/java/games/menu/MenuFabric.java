package games.menu;

import games.GameFabric;
import games.GameFabricSuper;
import games.menu.Menu;
import task.MenuTaskCreator;
import customer.CustomerState;

/**
 * MenuFabric
 */
public class MenuFabric
extends GameFabric<Menu> {
    private GameFabricSuper[] fabrics;

    public MenuFabric(GameFabricSuper... m_fabrics) {
        this.fabrics = m_fabrics;
    }

    @Override
    public Menu newGame(CustomerState state) {
        return new Menu(new MenuTaskCreator(), state, this.fabrics);
    }

    @Override
    public String getGameName() {
        return "menu";
    }
}
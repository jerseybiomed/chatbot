package games.menu;

import customer.CustomerState;
import games.GameFabric;
import task.MenuTaskCreator;

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
        return new Menu(new MenuTaskCreator(), state, this.fabrics);
    }

    @Override
    public String getGameName() {
        return Menu.getName();
    }
}
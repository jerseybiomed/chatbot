package games.menu;

import customer.CustomerState;
import games.GameFabric;

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
        return new Menu(state, this.fabrics);
    }

    @Override
    public String getGameName() {
        return Menu.getName();
    }
}
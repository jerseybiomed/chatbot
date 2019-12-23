package games;

import games.menu.Menu;

/**
 * GameFabricSuper
 */
public abstract class GameFabricSuper<T extends GameClient>
extends GameFabric<T> {
    protected Menu menu;

    public void setMenu(final Menu m_menu) {
        this.menu = m_menu;
    }
}
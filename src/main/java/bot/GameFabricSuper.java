package bot;

/**
 * GameFabricSuper
 */
public abstract class GameFabricSuper<T extends Game>
extends GameFabric<T> {
    protected Menu menu;

    public void setMenu(final Menu m_menu) {
        this.menu = m_menu;
    }
}
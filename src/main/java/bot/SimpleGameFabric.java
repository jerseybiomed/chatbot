package bot;

/**
 * SimpleGameFabric
 */
public class SimpleGameFabric
extends GameFabricSuper<SimpleGame> {

    @Override
    public SimpleGame newGame(CustomerState state) {
        return new SimpleGame(this.menu, state);
    }

    @Override
    public String getGameName() {
        return "simpleGame";
    }
}
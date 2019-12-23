package bot;

/**
 * SimpleGameFabric
 */
public class SimpleGameFabric
extends GameFabric<SimpleGame> {

    @Override
    public SimpleGame newGame(CustomerState state) {
        return new SimpleGame(state);
    }

    @Override
    public String getGameName() {
        return "simpleGame";
    }
}
package bot;

/**
 * GameFabric
 */
public abstract class GameFabric<T extends Game> {

    public abstract T newGame(final CustomerState state);
    public abstract String getGameName();
}
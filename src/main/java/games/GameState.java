package games;

import customer.CustomerState;

/**
 * GameState
 */
public class GameState {
    public final CustomerState exState;
    
    public GameState(CustomerState focus) {
        this.exState = focus;
    }
}
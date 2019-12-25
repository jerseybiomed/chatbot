package games;

import customer.CustomerState;

/**
 * GameState
 */
public class GameState {
    public final CustomerState focus;
    
    public GameState(CustomerState focus) {
        this.focus = focus;
    }
}
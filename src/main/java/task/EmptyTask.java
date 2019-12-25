package task;

import games.GameClient;
import logic.Sender;

/**
 * EmptyTask
 */
public class EmptyTask
extends Task<GameClient> {

    @Override
    public void perform(GameClient gameClient, Sender<String> sender) {}
}
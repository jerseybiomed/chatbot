package logic;

import logic.telegram.Player;

public interface Publisher<T> {
    void say(Player player, T message);
    void reply(Player player, T answer);
}

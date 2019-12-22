package logic;

import logic.telegram.Player;

public interface Subscriber<T> {
    void listen(Publisher<T> from, Player player, T data);
}

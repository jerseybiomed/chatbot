package logic;

import game.UserId;

public interface Subscriber<T> {
    void listen(UserId id, T data);
}

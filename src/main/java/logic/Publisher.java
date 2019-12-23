package logic;

import game.UserId;

public interface Publisher<T> {
    void publish(UserId id, T message);
}

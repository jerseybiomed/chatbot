package logic.telegram;

import game.UserId;
import jdk.internal.loader.AbstractClassLoaderValue;
import logic.Connector;
import logic.Publisher;
import logic.Subscriber;
import logic.dialog.Casino;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TelegramRequestCreator
        implements Subscriber<String>, Publisher<String>, Connector<Subscriber<String>> {

    private Casino casion;
    private List<Subscriber<String>> subscribers;

    TelegramRequestCreator(Casino casino) {
        this.casion = casino;
        this.subscribers = new ArrayList<>();
    }

    @Override
    public void connect(Subscriber<String> subscriber) {
        this.subscribers.add(subscriber);
    }

    @Override
    public void publish(UserId id, String message) {

    }

    @Override
    public void listen(UserId id, String data) {

    }
}

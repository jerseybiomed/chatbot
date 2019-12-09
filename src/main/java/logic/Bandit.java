package logic;

import java.util.AbstractMap.SimpleEntry;

public class Bandit {
    private BanditDrum drum = new BanditDrum(new Calculator());

    public SimpleEntry<String, Double> game(int bet) {
        return new SimpleEntry<>(drum.getComb(), drum.roll(bet));
    }
}

package games.roulette;

import random.Randomize;

public class Roulette {
    private Randomize randomize;
    private String request = "https://www.random.org/integers/?num=10&min=0&max=36&col=1&base=10&format=plain&rnd=new";

    public Roulette(Randomize randomize) {
        this.randomize = randomize;
    }

    public int getNext() {
        return this.randomize.Next(this.request);
    }
}

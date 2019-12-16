package game;

import web.Randomize;
import java.io.IOException;

/**
 * BanditDrum
 */
public class BanditDrum {
    private String currentComb = "000";
    private Randomize randomize;

    public BanditDrum(Randomize randomize) {
        this.randomize = randomize;
    }

    public void nextCombination() {
        String request = "https://www.random.org/integers/?num=10&min=1&max=50&col=1&base=10&format=plain&rnd=new";
        String combination = "";
        for (int i=0; i < 3; i++) {
            try {
                int random = randomize.Next(request);
                combination += (Integer.parseInt(currentComb.substring(i, i + 1)) + random) % 10;
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        currentComb = combination;
    }

    public int getCoefficient() {
        Combinator combinator = new Combinator();
        if (combinator.isFirstClassWinnerComb(currentComb))
            return 3;
        else if (combinator.isSecondClassWinnerComb(currentComb))
            return 2;
        else if (combinator.isBeautifulComb(currentComb))
            return 1;
        return 0;
    }

    public String getCombination() {
        return currentComb;
    }
}
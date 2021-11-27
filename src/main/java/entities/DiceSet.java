package entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiceSet {
   private final List<Integer> dices = new ArrayList<>();

    public DiceSet(Integer dice1, Integer dice2, Integer dice3, Integer dice4, Integer dice5) {
        this.dices.addAll(Arrays.asList(dice1, dice2,dice3, dice4,dice5));
    }

    public List<Integer> getDices() {
        return this.dices;
    }

    public int getFirstDice(){
        return this.dices.get(0);
    }
}
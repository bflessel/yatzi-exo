package entities;

import java.util.Arrays;
import java.util.List;

public class DiceSet {
    private final int dice1;
    private final int dice2;
    private final int dice3;
    private final int dice4;
    private final int dice5;

    public DiceSet(int dice1, int dice2, int dice3, int dice4, int dice5) {
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.dice3 = dice3;
        this.dice4 = dice4;
        this.dice5 = dice5;
    }

    public List<Integer> getDices() {
        return Arrays.asList(dice1,dice2,dice3,dice4,dice5);
    }
}
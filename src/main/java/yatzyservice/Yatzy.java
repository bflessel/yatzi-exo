package yatzyservice;

import entities.DiceSet;
import yatzyservice.helper.YatzyHelper;

import java.util.*;

public class Yatzy {
    private Yatzy() {
    }

    public static int getChanceScore(DiceSet diceSet) {
        return diceSet.getChanceScore();
    }

    public static int getYatzyScore(DiceSet diceSet) {
        return diceSet.getYatzyScore();
    }

    public static int getDiceOccurences(DiceSet diceSet, int diceNumber) {
        return diceSet.getDiceOccurences(diceNumber);
    }

    public static int calculatePairScore(DiceSet diceSet) {
        return YatzyHelper.calculateAllRepeatedValues(diceSet.getSortedValues(), false, 2);
    }

    public static int calculateDoublePairScore(DiceSet diceSet) {
        return YatzyHelper.calculateAllRepeatedValues(diceSet.getSortedValues(), true, 2);
    }

    public static int calculateFourOfAKindScore(DiceSet diceSet) {
        return YatzyHelper.calculateAllRepeatedValues(diceSet.getSortedValues(), true, 4);
    }

    public static int calculateThreeOfAKindScore(DiceSet diceSet) {
        return YatzyHelper.calculateAllRepeatedValues(diceSet.getSortedValues(), true, 3);
    }


    public static int calculateSmallStraightScore(DiceSet diceSet) {
        return diceSet.isSmallStraight() ? 15 : 0;
    }

    public static int calculateLargeStraightScore(DiceSet diceSet) {
        return diceSet.isLargeStraight() ? 20 : 0;

    }

    public static int fullHouse(DiceSet diceSet) {
        Map<Integer, Long> sortedValues = diceSet.getSortedValues();
        List<Long> keys = new ArrayList<>(sortedValues.values());
        if (YatzyHelper.isFullHouse(keys)) {
            return sortedValues.keySet().stream().reduce(0, (total, value) -> YatzyHelper.increaseTotal(sortedValues, total, value));
        }
        return 0;
    }


}




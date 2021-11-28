package yatzyservice;

import entities.DiceSet;
import yatzyservice.helper.YatzyHelper;

import java.util.*;
import java.util.stream.Collectors;

public class Yatzy {
    public static final int SMALL_STRAIGHT_SCORE = 15;
    public static final int LARGE_STRAIGHT_SCORE = 20;
    public static final int NO_SCORE = 0;
    public static final int THREE_OF_A_KIND = 3;
    public static final int FOUR_OF_A_KIND = 4;
    public static final int PAIR = 2;
    public static final int YATZY_NUMBER_OF_VALUES = 1;
    public static final int YATZY_VSCORE = 50;

    private Yatzy() {
    }

    public static Integer getChanceScore(DiceSet diceSet) {
        return diceSet.getChanceScore();
    }

    public static Integer getYatzyScore(DiceSet diceSet) {
        return diceSet.getYatzyScore() == YATZY_NUMBER_OF_VALUES ? YATZY_VSCORE : NO_SCORE;
    }

    public static Integer calculateNumbersScore(DiceSet diceSet, Integer diceNumber) {
        return diceSet.getDicesSum(diceNumber);
    }

    public static Integer calculatePairScore(DiceSet diceSet) {
        return YatzyHelper.calculateAllRepeatedValues(diceSet.getSortedValues(), false, PAIR);
    }

    public static Integer calculateDoublePairScore(DiceSet diceSet) {
        return YatzyHelper.calculateAllRepeatedValues(diceSet.getSortedValues(), true, PAIR);
    }

    public static Integer calculateFourOfAKindScore(DiceSet diceSet) {
        return YatzyHelper.calculateAllRepeatedValues(diceSet.getSortedValues(), true, FOUR_OF_A_KIND);
    }

    public static Integer calculateThreeOfAKindScore(DiceSet diceSet) {
        return YatzyHelper.calculateAllRepeatedValues(diceSet.getSortedValues(), true, THREE_OF_A_KIND);
    }


    public static Integer calculateSmallStraightScore(DiceSet diceSet) {
        return diceSet.isSmallStraight() ? SMALL_STRAIGHT_SCORE : NO_SCORE;
    }

    public static Integer calculateLargeStraightScore(DiceSet diceSet) {
        return diceSet.isLargeStraight() ? LARGE_STRAIGHT_SCORE : NO_SCORE;

    }

    public static Integer calculateFUllHouseScore(DiceSet diceSet) {
        Map<Integer, Long> sortedValues = diceSet.getSortedValues();
        List<Long> keys = new ArrayList<>(Collections.unmodifiableList(sortedValues.values().stream().sorted().collect((Collectors.toList()))));
        if (YatzyHelper.isFullHouse(keys)) {
            return sortedValues.keySet().stream().reduce(0, (total, value) -> YatzyHelper.increaseTotal(sortedValues, total, value));
        }
        return 0;
    }


}




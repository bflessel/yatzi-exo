package yatzyservice;

import entities.DiceSet;

import java.util.*;
import java.util.stream.Collectors;

public class Yatzy {
    private static final int SMALL_STRAIGHT_SCORE = 15;
    private static final int LARGE_STRAIGHT_SCORE = 20;
    private static final int NO_SCORE = 0;
    private static final int THREE_OF_A_KIND = 3;
    private static final int FOUR_OF_A_KIND = 4;
    private static final int PAIR = 2;
    private static final int YATZY_NUMBER_OF_VALUES = 1;
    private static final int YATZY_VSCORE = 50;

    private Yatzy() {
    }

    public static Integer getChanceScore(DiceSet diceSet) {
        return diceSet.getDices().stream().mapToInt(Integer::intValue).sum();
    }

    public static Integer getYatzyScore(DiceSet diceSet) {
        return diceSet.getDices().stream().mapToInt(Integer::intValue).distinct().count()
            == YATZY_NUMBER_OF_VALUES ? YATZY_VSCORE : NO_SCORE;
    }

    public static Integer calculateNumbersScore(DiceSet diceSet, Integer diceNumber) {
        return getDicesSum(diceSet, diceNumber);
    }

    public static Integer calculatePairScore(DiceSet diceSet) {
        return calculateAllPairValues(diceSet.getSortedValues());
    }

    public static Integer calculateDoublePairScore(DiceSet diceSet) {
        return calculateAllRepeatedValues(diceSet.getSortedValues(), PAIR);
    }

    public static Integer calculateFourOfAKindScore(DiceSet diceSet) {
        return calculateAllRepeatedValues(diceSet.getSortedValues(), FOUR_OF_A_KIND);
    }

    public static Integer calculateThreeOfAKindScore(DiceSet diceSet) {
        return calculateAllRepeatedValues(diceSet.getSortedValues(), THREE_OF_A_KIND);
    }


    public static Integer calculateSmallStraightScore(DiceSet diceSet) {
        return diceSet.isSmallStraight() ? SMALL_STRAIGHT_SCORE : NO_SCORE;
    }

    public static Integer calculateLargeStraightScore(DiceSet diceSet) {
        return diceSet.isLargeStraight() ? LARGE_STRAIGHT_SCORE : NO_SCORE;

    }

    public static Integer calculateFullHouseScore(DiceSet diceSet) {
        Map<Integer, Long> sortedValues = diceSet.getSortedValues();
        List<Long> keys = sortedValues.values().stream().sorted().collect(Collectors.toList());
        if ((keys.get(0) == PAIR && keys.get(1) == THREE_OF_A_KIND)) {
            return sortedValues.keySet().stream()
                .reduce(0, (total, value) -> total + (sortedValues.get(value).intValue() * value));
        }
        return NO_SCORE;
    }


    public static Integer calculateAllRepeatedValues(Map<Integer, Long> sortedValues, int size) {
        Integer repeatedValue = sortedValues.keySet().stream()
            .filter(e -> sortedValues.get(e).intValue() >= size)
            .reduce(0, (total, diceValue) -> total + diceValue * size);
        return repeatedValue != null ? repeatedValue : Integer.valueOf(NO_SCORE);
    }

    public static Integer calculateAllPairValues(Map<Integer, Long> sortedValues) {
        Integer pairValue = sortedValues.keySet().stream()
            .filter(e -> sortedValues.get(e).intValue() >= 2)
            .findFirst().stream()
            .reduce(0, (total, diceValue) -> total + diceValue * 2);
        return pairValue != null ? pairValue : Integer.valueOf(NO_SCORE);
    }

    static Integer getDicesSum(DiceSet diceSet, int diceNumber) {
        return diceSet.getDices().stream().mapToInt(Integer::intValue
        ).filter(e -> e == diceNumber).sum();
    }
}




package yatzyservice;

import entities.DiceSet;

import java.util.*;
import java.util.stream.Collectors;

public class Yatzy {

    private static final List<Integer> SMALL_STRAIGHT = Arrays.asList(1, 2, 3, 4, 5);
    private static final List<Integer> LARGE_STRAIGHT = Arrays.asList(2, 3, 4, 5, 6);

    public static int getChanceScore(DiceSet diceSet) {
        return diceSet.getDices().stream().mapToInt(Integer::intValue).sum();
    }

    public static int getYatzyScore(DiceSet diceSet) {
        return diceSet.getDices().stream().mapToInt(Integer::intValue).allMatch(Integer.valueOf(diceSet.getFirstDice())::equals) ? 50 : 0;
    }

    public static int getNumberOfAKindScore(DiceSet diceSet, int diceNumber) {

        return diceSet.getDices().stream().mapToInt(Integer::intValue).filter(e -> e == diceNumber).sum();
    }

    private static Map<Integer, Long> getSortedValues(DiceSet diceSet) {
        return diceSet.getDices()
            .stream().sorted(Comparator.reverseOrder())
            .collect(Collectors.groupingBy(element -> element, LinkedHashMap::new, Collectors.counting()));
    }

    public static int calculatePairScore(DiceSet diceSet) {
        return calculateAllRepeatedValues(diceSet, false, 2);
    }

    public static int calculateDoublePairScore(DiceSet diceSet) {
        return calculateAllRepeatedValues(diceSet, true, 2);
    }

    public static int calculateFourOfAKindScore(DiceSet diceSet) {
        return calculateAllRepeatedValues(diceSet, true, 4);
    }

    public static int calculateThreeOfAKindScore(DiceSet diceSet) {
        return calculateAllRepeatedValues(diceSet, true, 3);
    }


    public static int calculateSmallStraightScore(DiceSet diceSet) {
        return diceSet.getDices().containsAll(SMALL_STRAIGHT) ? 15 : 0;
    }

    public static int calculateLargeStraightScore(DiceSet diceSet) {
        return diceSet.getDices().containsAll(LARGE_STRAIGHT) ? 20 : 0;

    }

    public static int fullHouse(DiceSet diceSet) {
        Map<Integer, Long> sortedValues = getSortedValues(diceSet);
        List<Long> keys = new ArrayList<>(sortedValues.values());
        if (isFullHouse(keys)) {
            return sortedValues.keySet().stream().reduce(0, (total, value) -> increaseTotal(sortedValues, total, value));
        }
        return 0;
    }

    private static int calculateAllRepeatedValues(DiceSet diceSet, boolean multiples, int size) {
        Map<Integer, Long> sortedValues = getSortedValues(diceSet);
        Integer pairValue = getRepeatedValues(sortedValues, multiples, size);
        return pairValue != null ? pairValue : Integer.valueOf(0);
    }

    private static Integer getRepeatedValues(Map<Integer, Long> sortedValues, boolean multiple, int size) {
        if (!multiple) {
            return sortedValues.keySet().stream().filter(e -> sortedValues.get(e).intValue() >= size).findFirst().stream().reduce(0, (total, diceValue) -> total + diceValue * size);
        } else {
            return sortedValues.keySet().stream().filter(e -> sortedValues.get(e).intValue() >= size).reduce(0, (total, diceValue) -> total + diceValue * size);
        }
    }

    private static boolean isFullHouse(List<Long> keys) {
        return (keys.get(0) == 2 && keys.get(1) == 3) || (keys.get(0) == 3 && keys.get(1) == 2);
    }

    private static int increaseTotal(Map<Integer, Long> sortedValues, Integer total, Integer value) {
        return total + (sortedValues.get(value).intValue() * value);
    }
}




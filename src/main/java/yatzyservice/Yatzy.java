package yatzyservice;

import entities.DiceSet;

import java.util.*;
import java.util.stream.Collectors;

public class Yatzy {

    private static final List<Integer> SMALL_STRAIGHT = Arrays.asList(1, 2, 3, 4, 5);
    private static final List<Integer> LARGE_STRAIGHT = Arrays.asList(2, 3, 4, 5, 6);

    public static int chance(DiceSet diceSet) {
        return diceSet.getDices().stream().mapToInt(Integer::intValue).sum();
    }

    public static int yatzy(DiceSet diceSet) {
        return diceSet.getDices().stream().mapToInt(Integer::intValue).allMatch(Integer.valueOf(diceSet.getFirstDice())::equals) ? 50 : 0;
    }


    public static int countDice(DiceSet diceSet, int diceNumber) {
        return diceSet.getDices().stream().mapToInt(Integer::intValue).filter(e -> e == diceNumber).sum();
    }

    public static int calculatePairScore(DiceSet diceSet) {
        return calculateAllRepeatedValues(diceSet, false, 2);
    }

    private static Map<Integer, Long> getSortedValues(DiceSet diceSet) {
        return diceSet.getDices()
            .stream().sorted(Comparator.reverseOrder())
            .collect(Collectors.groupingBy(element -> element, LinkedHashMap::new, Collectors.counting()));
    }

    public static int calculateDoublePairScore(DiceSet diceSet) {
        return calculateAllRepeatedValues(diceSet, true, 2);
    }

    private static int calculateAllRepeatedValues(DiceSet diceSet, boolean multiples, int size) {
        Map<Integer, Long> sortedValues = getSortedValues(diceSet);
        Integer pairValue = getRepeatedValues(sortedValues, multiples, size);
        if (pairValue != null) {
            return pairValue;
        }
        return 0;
    }

    private static Integer getRepeatedValues(Map<Integer, Long> sortedValues, boolean multiple, int size) {
        int count = 0;
        for (Integer diceValue : sortedValues.keySet()) {
            if (sortedValues.get(diceValue).intValue() >= size) {
                count += diceValue * size;
                if (!multiple) {
                    break;
                }
            }
        }
        return count;


    }

    public static int calculateForOfAKindValue(DiceSet diceSet) {
        return calculateAllRepeatedValues(diceSet, true, 4);
    }

    public static int calculateThreeOfAKindValue(DiceSet diceSet) {
        return calculateAllRepeatedValues(diceSet, true, 3);
    }


    public static int calculateSmallStraightValue(DiceSet diceSet) {
        return diceSet.getDices().containsAll(SMALL_STRAIGHT) ? 15 : 0;
    }

    public static int calculateLargeStraightValue(DiceSet diceSet) {
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

    private static boolean isFullHouse(List<Long> keys) {
        return (keys.get(0) == 2 && keys.get(1) == 3) || (keys.get(0) == 3 && keys.get(1) == 2);
    }

    private static int increaseTotal(Map<Integer, Long> sortedValues, Integer total, Integer value) {
        return total + (sortedValues.get(value).intValue() * value);
    }
}




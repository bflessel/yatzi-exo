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
        return calculateAllRepetedValues(diceSet, false, 2);
    }

    private static Map<Integer, Long> getSortedValues(DiceSet diceSet) {
        return diceSet.getDices()
            .stream().sorted(Comparator.reverseOrder())
            .collect(Collectors.groupingBy(element -> element, LinkedHashMap::new, Collectors.counting()));
    }

    public static int calculateDoublePairScore(DiceSet diceSet) {
        return calculateAllRepetedValues(diceSet, true, 2);
    }

    private static int calculateAllRepetedValues(DiceSet diceSet, boolean multiples, int size) {
        Map<Integer, Long> sortedValues = getSortedValues(diceSet);
        Integer pairValue = getRepetedValues(sortedValues, multiples, size);
        if (pairValue != null) {
            return pairValue;
        }
        return 0;
    }

    private static Integer getRepetedValues(Map<Integer, Long> sortedValues, boolean multiple, int size) {
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
        return calculateAllRepetedValues(diceSet, true, 4);
    }

    public static int calculateThreeOfAKindValue(DiceSet diceSet) {
        return calculateAllRepetedValues(diceSet, true, 3);
    }


    public static int calculateSmallStraightValue(DiceSet diceSet) {
        return diceSet.getDices().containsAll(SMALL_STRAIGHT)? 15 : 0;
    }

    public static int calculateLargeStraightValue(DiceSet diceSet) {
        return diceSet.getDices().containsAll(LARGE_STRAIGHT)? 20 : 0;

    }

    public static int fullHouse(int d1, int d2, int d3, int d4, int d5) {
        int[] tallies;
        boolean _2 = false;
        int i;
        int _2_at = 0;
        boolean _3 = false;
        int _3_at = 0;


        tallies = new int[6];
        tallies[d1 - 1] += 1;
        tallies[d2 - 1] += 1;
        tallies[d3 - 1] += 1;
        tallies[d4 - 1] += 1;
        tallies[d5 - 1] += 1;

        for (i = 0; i != 6; i += 1) {
            if (tallies[i] == 2) {
                _2 = true;
                _2_at = i + 1;
            }
        }

        for (i = 0; i != 6; i += 1) {
            if (tallies[i] == 3) {
                _3 = true;
                _3_at = i + 1;
            }
        }

        if (_2 && _3) {
            return _2_at * 2 + _3_at * 3;
        } else {
            return 0;
        }
    }
}




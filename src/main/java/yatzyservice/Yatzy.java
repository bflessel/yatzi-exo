package yatzyservice;

import entities.DiceSet;

import java.util.*;
import java.util.stream.Collectors;

public class Yatzy {

    public static int chance(DiceSet diceSet) {
        return diceSet.getDices().stream().mapToInt(Integer::intValue).sum();
    }

    public static int yatzy(DiceSet diceSet) {
        return diceSet.getDices().stream().mapToInt(Integer::intValue).allMatch(Integer.valueOf(diceSet.getFirstDice())::equals) ? 50 : 0;
    }


    public static int countDice(DiceSet diceSet, int diceNumber) {
        return diceSet.getDices().stream().mapToInt(Integer::intValue).filter(e -> e == diceNumber).sum();
    }

    protected int[] dice;

    public Yatzy(int d1, int d2, int d3, int d4, int _5) {
        dice = new int[5];
        dice[0] = d1;
        dice[1] = d2;
        dice[2] = d3;
        dice[3] = d4;
        dice[4] = _5;
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


    public static int smallStraight(int d1, int d2, int d3, int d4, int d5) {
        int[] tallies;
        tallies = new int[6];
        tallies[d1 - 1] += 1;
        tallies[d2 - 1] += 1;
        tallies[d3 - 1] += 1;
        tallies[d4 - 1] += 1;
        tallies[d5 - 1] += 1;
        if (tallies[0] == 1 &&
            tallies[1] == 1 &&
            tallies[2] == 1 &&
            tallies[3] == 1 &&
            tallies[4] == 1) {
            return 15;
        }
        return 0;
    }

    public static int largeStraight(int d1, int d2, int d3, int d4, int d5) {
        int[] tallies;
        tallies = new int[6];
        tallies[d1 - 1] += 1;
        tallies[d2 - 1] += 1;
        tallies[d3 - 1] += 1;
        tallies[d4 - 1] += 1;
        tallies[d5 - 1] += 1;
        if (tallies[1] == 1 &&
            tallies[2] == 1 &&
            tallies[3] == 1 &&
            tallies[4] == 1
            && tallies[5] == 1) {
            return 20;
        }
        return 0;
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




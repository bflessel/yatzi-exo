package entities;

import java.util.*;
import java.util.stream.Collectors;

public class DiceSet {
    private final List<Integer> dices = new ArrayList<>();

    private static final List<Integer> SMALL_STRAIGHT = Arrays.asList(1, 2, 3, 4, 5);
    private static final List<Integer> LARGE_STRAIGHT = Arrays.asList(2, 3, 4, 5, 6);

    public DiceSet(Integer dice1, Integer dice2, Integer dice3, Integer dice4, Integer dice5) {
        this.dices.addAll(Arrays.asList(dice1, dice2, dice3, dice4, dice5));
    }

    public int getChanceScore() {
        return dices.stream().mapToInt(Integer::intValue).sum();
    }

    public int getYatzyScore() {
        return dices.stream().mapToInt(Integer::intValue).distinct().count() == 1 ? 50 : 0;
    }

    public int getDiceOccurences(int diceNumber) {
        return dices.stream().mapToInt(Integer::intValue).filter(e -> e == diceNumber).sum();
    }

    public Map<Integer, Long> getSortedValues() {
        return dices.stream().sorted(Comparator.reverseOrder())
            .collect(Collectors.groupingBy(element -> element, LinkedHashMap::new, Collectors.counting()));
    }

    public boolean isSmallStraight(){
        return dices.containsAll(SMALL_STRAIGHT);
    }
    public boolean isLargeStraight(){
        return dices.containsAll(LARGE_STRAIGHT);
    }
}
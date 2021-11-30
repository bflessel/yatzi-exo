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

    public static DiceSet of(Integer dice1, Integer dice2, Integer dice3, Integer dice4, Integer dice5){
        return new DiceSet(dice1, dice2, dice3, dice4, dice5);
    }

    public  List<Integer> getDices() {
        return Collections.unmodifiableList(dices);
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
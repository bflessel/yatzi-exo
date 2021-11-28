package yatzyservice.helper;

import java.util.List;
import java.util.Map;

public class YatzyHelper {
    private YatzyHelper() {
    }

    public static boolean isFullHouse(List<Long> keys) {
        return (keys.get(0) == 2 && keys.get(1) == 3);
    }

    public static Integer increaseTotal(Map<Integer, Long> sortedValues, Integer total, Integer value) {
        return total + (sortedValues.get(value).intValue() * value);
    }

    public static Integer calculateAllRepeatedValues(Map<Integer, Long> sortedValues, boolean multiples, int size) {
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
}

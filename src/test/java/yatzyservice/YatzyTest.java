package yatzyservice;

import entities.DiceSet;
import org.junit.*;

import static org.junit.Assert.*;

public class YatzyTest {

    @Test
    public void player_should_scores_sum_of_all_dice() {
        int expected = 15;
        int actual = Yatzy.getChanceScore(new DiceSet(2, 3, 4, 5, 1));
        int expected2 = 16;
        int actual2 = Yatzy.getChanceScore(new DiceSet(3, 3, 4, 5, 1));
        assertEquals(expected, actual);
        assertEquals(expected2, actual2);
    }

    @Test
    public void player_should_score_50_when_yatzy_with_all_five_dice_equals() {
        int expected = 50;
        int actual = Yatzy.getYatzyScore(new DiceSet(4, 4, 4, 4, 4));
        int actual2 = Yatzy.getYatzyScore(new DiceSet(6, 6, 6, 6, 6));
        assertEquals(expected, actual);
        assertEquals(expected, actual2);
    }

    @Test
    public void player_should_score_0_when_yatzy_with_not_all_five_dice_equals() {
        int expected = 0;
        int actual = Yatzy.getYatzyScore(new DiceSet(6, 6, 6, 6, 3));
        assertEquals(expected, actual);
    }

    @Test
    public void player_should_score_one_points_by_each_dice_with_DiceOccuences_at_1() {
        assertEquals(1, Yatzy.calculateNumbersScore(new DiceSet(1, 2, 3, 4, 5), 1).intValue());
        assertEquals(2, Yatzy.calculateNumbersScore(new DiceSet(1, 2, 1, 4, 5), 1).intValue());
        assertEquals(0, Yatzy.calculateNumbersScore(new DiceSet(6, 2, 2, 4, 5), 1).intValue());
        assertEquals(4, Yatzy.calculateNumbersScore(new DiceSet(1, 2, 1, 1, 1), 1).intValue());
    }

    @Test
    public void player_should_score_one_points_by_each_dice_with_DiceOccuences_at_2() {
        assertEquals(4, Yatzy.calculateNumbersScore(new DiceSet(1, 2, 3, 2, 6), 2).intValue());
        assertEquals(10, Yatzy.calculateNumbersScore(new DiceSet(2, 2, 2, 2, 2), 2).intValue());
        assertEquals(0, Yatzy.calculateNumbersScore(new DiceSet(6, 5, 3, 1, 4), 2).intValue());

    }

    @Test
    public void player_should_score_one_points_by_each_dice_with_DiceOccuences_at_3() {
        assertEquals(6, Yatzy.calculateNumbersScore(new DiceSet(1, 2, 3, 2, 3), 3).intValue());
        assertEquals(12, Yatzy.calculateNumbersScore(new DiceSet(2, 3, 3, 3, 3), 3).intValue());
        assertEquals(0, Yatzy.calculateNumbersScore(new DiceSet(6, 5, 4, 1, 2), 3).intValue());

    }

    @Test
    public void player_should_score_one_points_by_each_dice_with_DiceOccuences_at_4() {
        assertEquals(12, Yatzy.calculateNumbersScore(new DiceSet(4, 4, 4, 5, 5), 4).intValue());
        assertEquals(8, Yatzy.calculateNumbersScore(new DiceSet(4, 4, 5, 5, 5), 4).intValue());
        assertEquals(4, Yatzy.calculateNumbersScore(new DiceSet(4, 5, 5, 5, 5), 4).intValue());
        assertEquals(0, Yatzy.calculateNumbersScore(new DiceSet(6, 5, 3, 1, 2), 4).intValue());

    }

    @Test
    public void player_should_score_one_points_by_each_dice_with_DiceOccuences_at_5() {
        assertEquals(10, Yatzy.calculateNumbersScore(new DiceSet(4, 4, 4, 5, 5), 5).intValue());
        assertEquals(15, Yatzy.calculateNumbersScore(new DiceSet(4, 4, 5, 5, 5), 5).intValue());
        assertEquals(20, Yatzy.calculateNumbersScore(new DiceSet(4, 5, 5, 5, 5), 5).intValue());
        assertEquals(0, Yatzy.calculateNumbersScore(new DiceSet(4, 4, 3, 1, 2), 5).intValue());

    }

    @Test
    public void player_should_score_one_points_by_each_dice_with_DiceOccuences_at_6() {
        assertEquals(0, Yatzy.calculateNumbersScore(new DiceSet(4, 4, 4, 5, 5), 6).intValue());
        assertEquals(6, Yatzy.calculateNumbersScore(new DiceSet(4, 4, 6, 5, 5), 6).intValue());
        assertEquals(18, Yatzy.calculateNumbersScore(new DiceSet(6, 5, 6, 6, 5), 6).intValue());
    }

    @Test
    public void player_should_score_higher_pair_value_when_calculate__pair_score() {
        assertEquals(6, Yatzy.calculatePairScore(new DiceSet(3, 4, 3, 5, 6)).intValue());
        assertEquals(10, Yatzy.calculatePairScore(new DiceSet(5, 3, 3, 3, 5)).intValue());
        assertEquals(12, Yatzy.calculatePairScore(new DiceSet(5, 3, 6, 6, 5)).intValue());
        assertEquals(0, Yatzy.calculatePairScore(new DiceSet(1, 2, 3, 4, 5)).intValue());
    }

    @Test
    public void player_should_score_all_pair_value_when_calculate_double_pair_score() {
        assertEquals(16, Yatzy.calculateDoublePairScore(new DiceSet(3, 3, 5, 4, 5)).intValue());
        assertEquals(16, Yatzy.calculateDoublePairScore(new DiceSet(3, 3, 5, 5, 5)).intValue());
        assertEquals(0, Yatzy.calculateDoublePairScore(new DiceSet(1, 2, 3, 4, 5)).intValue());
    }

    @Test
    public void player_should_score_three_same_dice_value_score_when_calculate_Three_of_a_kind_Score() {
        assertEquals(9, Yatzy.calculateThreeOfAKindScore(new DiceSet(3, 3, 3, 4, 5)).intValue());
        assertEquals(15, Yatzy.calculateThreeOfAKindScore(new DiceSet(5, 3, 5, 4, 5)).intValue());
        assertEquals(9, Yatzy.calculateThreeOfAKindScore(new DiceSet(3, 3, 3, 3, 5)).intValue());
        assertEquals(9, Yatzy.calculateThreeOfAKindScore(new DiceSet(3, 3, 3, 3, 3)).intValue());
        assertEquals(0, Yatzy.calculateThreeOfAKindScore(new DiceSet(1, 2, 3, 4, 5)).intValue());
    }

    @Test
    public void player_should_score_four_same_dice_value_score_when_calculate_Four_of_a_kind_Score() {
        assertEquals(12, Yatzy.calculateFourOfAKindScore(new DiceSet(3, 3, 3, 3, 5)).intValue());
        assertEquals(20, Yatzy.calculateFourOfAKindScore(new DiceSet(5, 5, 5, 4, 5)).intValue());
        assertEquals(0, Yatzy.calculateFourOfAKindScore(new DiceSet(1, 2, 3, 4, 5)).intValue());

    }

    @Test
    public void player_should_calculate_small_straight_score() {
        assertEquals(15, Yatzy.calculateSmallStraightScore(new DiceSet(1, 2, 3, 4, 5)).intValue());
        assertEquals(15, Yatzy.calculateSmallStraightScore(new DiceSet(2, 3, 4, 5, 1)).intValue());
        assertEquals(0, Yatzy.calculateSmallStraightScore(new DiceSet(1, 2, 2, 4, 5)).intValue());
        assertEquals(0, Yatzy.calculateSmallStraightScore(new DiceSet(1, 2, 6, 4, 5)).intValue());
    }

    @Test
    public void player_should_calculate_large_straight_score() {
        assertEquals(20, Yatzy.calculateLargeStraightScore(new DiceSet(6, 2, 3, 4, 5)).intValue());
        assertEquals(20, Yatzy.calculateLargeStraightScore(new DiceSet(2, 3, 4, 5, 6)).intValue());
        assertEquals(0, Yatzy.calculateLargeStraightScore(new DiceSet(1, 2, 2, 4, 5)).intValue());
        assertEquals(0, Yatzy.calculateLargeStraightScore(new DiceSet(1, 2, 6, 4, 5)).intValue());
    }

    @Test
    public void player_should_calculate_full_house_score() {
        assertEquals(18, Yatzy.calculateFUllHouseScore(new DiceSet(6, 2, 2, 2, 6)).intValue());
        assertEquals(0, Yatzy.calculateFUllHouseScore(new DiceSet(2, 3, 4, 5, 6)).intValue());
        assertEquals(0, Yatzy.calculateFUllHouseScore(new DiceSet(2, 2, 6, 5, 6)).intValue());
    }
}

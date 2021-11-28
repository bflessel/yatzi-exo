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
    public void player_should_score_one_points_by_each_dice_with_one() {
        assertEquals(1, Yatzy.getNumberOfAKindScore(new DiceSet(1, 2, 3, 4, 5), 1));
        assertEquals(2, Yatzy.getNumberOfAKindScore(new DiceSet(1, 2, 1, 4, 5), 1));
        assertEquals(0, Yatzy.getNumberOfAKindScore(new DiceSet(6, 2, 2, 4, 5), 1));
        assertEquals(4, Yatzy.getNumberOfAKindScore(new DiceSet(1, 2, 1, 1, 1), 1));
    }

    @Test
    public void player_should_score_two_points_by_each_dice_with_two() {
        assertEquals(4, Yatzy.getNumberOfAKindScore(new DiceSet(1, 2, 3, 2, 6), 2));
        assertEquals(10, Yatzy.getNumberOfAKindScore(new DiceSet(2, 2, 2, 2, 2), 2));
    }

    @Test
    public void player_should_score_three_points_by_each_dice_with_three() {
        assertEquals(6, Yatzy.getNumberOfAKindScore(new DiceSet(1, 2, 3, 2, 3), 3));
        assertEquals(12, Yatzy.getNumberOfAKindScore(new DiceSet(2, 3, 3, 3, 3), 3));
    }

    @Test
    public void player_should_score_four_points_by_each_dice_with_four() {
        assertEquals(12, Yatzy.getNumberOfAKindScore(new DiceSet(4, 4, 4, 5, 5), 4));
        assertEquals(8, Yatzy.getNumberOfAKindScore(new DiceSet(4, 4, 5, 5, 5), 4));
        assertEquals(4, Yatzy.getNumberOfAKindScore(new DiceSet(4, 5, 5, 5, 5), 4));
    }

    @Test
    public void player_should_score_five_points_by_each_dice_with_five() {
        assertEquals(10, Yatzy.getNumberOfAKindScore(new DiceSet(4, 4, 4, 5, 5), 5));
        assertEquals(15, Yatzy.getNumberOfAKindScore(new DiceSet(4, 4, 5, 5, 5), 5));
        assertEquals(20, Yatzy.getNumberOfAKindScore(new DiceSet(4, 5, 5, 5, 5), 5));
    }

    @Test
    public void player_should_score_six_points_by_each_dice_with_six() {
        assertEquals(0, Yatzy.getNumberOfAKindScore(new DiceSet(4, 4, 4, 5, 5), 6));
        assertEquals(6, Yatzy.getNumberOfAKindScore(new DiceSet(4, 4, 6, 5, 5), 6));
        assertEquals(18, Yatzy.getNumberOfAKindScore(new DiceSet(6, 5, 6, 6, 5), 6));
    }

    @Test
    public void player_should_score_higher_pair_score() {
        assertEquals(6, Yatzy.calculatePairScore(new DiceSet(3, 4, 3, 5, 6)));
        assertEquals(10, Yatzy.calculatePairScore(new DiceSet(5, 3, 3, 3, 5)));
        assertEquals(12, Yatzy.calculatePairScore(new DiceSet(5, 3, 6, 6, 5)));
    }

    @Test
    public void player_should_score_all_pair_score() {
        assertEquals(16, Yatzy.calculateDoublePairScore(new DiceSet(3, 3, 5, 4, 5)));
        assertEquals(16, Yatzy.calculateDoublePairScore(new DiceSet(3, 3, 5, 5, 5)));
    }

    @Test
    public void player_should_score_three_of_a_kind_score() {
        assertEquals(9, Yatzy.calculateThreeOfAKindScore(new DiceSet(3, 3, 3, 4, 5)));
        assertEquals(15, Yatzy.calculateThreeOfAKindScore(new DiceSet(5, 3, 5, 4, 5)));
        assertEquals(9, Yatzy.calculateThreeOfAKindScore(new DiceSet(3, 3, 3, 3, 5)));
        assertEquals(9, Yatzy.calculateThreeOfAKindScore(new DiceSet(3, 3, 3, 3, 3)));

    }

    @Test
    public void player_should_score_four_of_a_kind_score() {
        assertEquals(12, Yatzy.calculateFourOfAKindScore(new DiceSet(3, 3, 3, 3, 5)));
        assertEquals(20, Yatzy.calculateFourOfAKindScore(new DiceSet(5, 5, 5, 4, 5)));
    }

    @Test
    public void player_should_calculate_small_straight_score() {
        assertEquals(15, Yatzy.calculateSmallStraightScore(new DiceSet(1, 2, 3, 4, 5)));
        assertEquals(15, Yatzy.calculateSmallStraightScore(new DiceSet(2, 3, 4, 5, 1)));
        assertEquals(0, Yatzy.calculateSmallStraightScore(new DiceSet(1, 2, 2, 4, 5)));
    }

    @Test
    public void player_should_calculate_large_straight_score() {
        assertEquals(20, Yatzy.calculateLargeStraightScore(new DiceSet(6, 2, 3, 4, 5)));
        assertEquals(20, Yatzy.calculateLargeStraightScore(new DiceSet(2, 3, 4, 5, 6)));
        assertEquals(0, Yatzy.calculateLargeStraightScore(new DiceSet(1, 2, 2, 4, 5)));
    }

    @Test
    public void player_should_calculate_full_house_score() {
        assertEquals(18, Yatzy.fullHouse(new DiceSet(6, 2, 2, 2, 6)));
        assertEquals(0, Yatzy.fullHouse(new DiceSet(2, 3, 4, 5, 6)));
    }
}

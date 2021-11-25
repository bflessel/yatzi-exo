package yatzyservice;

import entities.DiceSet;
import org.junit.*;

import static org.junit.Assert.*;

public class YatzyTest {

    @Test
    public void player_scores_sum_of_all_dice() {
        int expected = 15;
        int actual = Yatzy.chance(new DiceSet(2, 3, 4, 5, 1));
        int expected2 = 16;
        int actual2 = Yatzy.chance(new DiceSet(3, 3, 4, 5, 1));
        assertEquals(expected, actual);
        assertEquals(expected2, actual2);
    }

    @Test
    public void player_score_50_when_yatzy_with_all_five_dice_equals() {
        int expected = 50;
        int actual = Yatzy.yatzy(new DiceSet(4, 4, 4, 4, 4));
        int actual2 = Yatzy.yatzy(new DiceSet(6, 6, 6, 6, 6));
        assertEquals(expected, actual);
        assertEquals(expected, actual2);
    }

    @Test
    public void player_score_0_when_yatzy_with_not_all_five_dice_equals() {
        int expected = 0;
        int actual = Yatzy.yatzy(new DiceSet(6, 6, 6, 6, 3));
        assertEquals(expected, actual);
    }

    @Test
    public void player_score_one_points_by_each_dice_with_one() {
        assertEquals(1, Yatzy.countDice(new DiceSet(1, 2, 3, 4, 5), 1));
        assertEquals(2, Yatzy.countDice(new DiceSet(1, 2, 1, 4, 5), 1));
        assertEquals(0, Yatzy.countDice(new DiceSet(6, 2, 2, 4, 5), 1));
        assertEquals(4, Yatzy.countDice(new DiceSet(1, 2, 1, 1, 1), 1));
    }

    @Test
    public void player_score_two_points_by_each_dice_with_two() {
        assertEquals(4, Yatzy.countDice(new DiceSet(1, 2, 3, 2, 6), 2));
        assertEquals(10, Yatzy.countDice(new DiceSet(2, 2, 2, 2, 2), 2));
    }

    @Test
    public void player_score_three_points_by_each_dice_with_three() {
        assertEquals(6, Yatzy.countDice(new DiceSet(1, 2, 3, 2, 3), 3));
        assertEquals(12, Yatzy.countDice(new DiceSet(2, 3, 3, 3, 3), 3));
    }

    @Test
    public void player_score_four_points_by_each_dice_with_four() {
        assertEquals(12, Yatzy.countDice(new DiceSet(4, 4, 4, 5, 5), 4));
        assertEquals(8, Yatzy.countDice(new DiceSet(4, 4, 5, 5, 5), 4));
        assertEquals(4, Yatzy.countDice(new DiceSet(4, 5, 5, 5, 5), 4));
    }

    @Test
    public void player_score_five_points_by_each_dice_with_five() {
        assertEquals(10, Yatzy.countDice(new DiceSet(4, 4, 4, 5, 5), 5));
        assertEquals(15, Yatzy.countDice(new DiceSet(4, 4, 5, 5, 5), 5));
        assertEquals(20, Yatzy.countDice(new DiceSet(4, 5, 5, 5, 5), 5));
    }

    @Test
    public void player_score_six_points_by_each_dice_with_six() {
        assertEquals(0, Yatzy.countDice(new DiceSet(4, 4, 4, 5, 5), 6));
        assertEquals(6, Yatzy.countDice(new DiceSet(4, 4, 6, 5, 5), 6));
        assertEquals(18, Yatzy.countDice(new DiceSet(6, 5, 6, 6, 5), 6));
    }

    @Test
    public void one_pair() {
        assertEquals(6, Yatzy.calculatePairScore(new DiceSet(3, 4, 3, 5, 6)));
        assertEquals(10, Yatzy.calculatePairScore(new DiceSet(5, 3, 3, 3, 5)));
        assertEquals(12, Yatzy.calculatePairScore(new DiceSet(5, 3, 6, 6, 5)));
    }

    @Test
    public void two_Pair() {
        assertEquals(16, Yatzy.two_pair(3, 3, 5, 4, 5));
        assertEquals(16, Yatzy.two_pair(3, 3, 5, 5, 5));
    }

    @Test
    public void three_of_a_kind() {
        assertEquals(9, Yatzy.three_of_a_kind(3, 3, 3, 4, 5));
        assertEquals(15, Yatzy.three_of_a_kind(5, 3, 5, 4, 5));
        assertEquals(9, Yatzy.three_of_a_kind(3, 3, 3, 3, 5));
    }

    @Test
    public void four_of_a_knd() {
        assertEquals(12, Yatzy.four_of_a_kind(3, 3, 3, 3, 5));
        assertEquals(20, Yatzy.four_of_a_kind(5, 5, 5, 4, 5));
        assertEquals(9, Yatzy.three_of_a_kind(3, 3, 3, 3, 3));
    }

    @Test
    public void smallStraight() {
        assertEquals(15, Yatzy.smallStraight(1, 2, 3, 4, 5));
        assertEquals(15, Yatzy.smallStraight(2, 3, 4, 5, 1));
        assertEquals(0, Yatzy.smallStraight(1, 2, 2, 4, 5));
    }

    @Test
    public void largeStraight() {
        assertEquals(20, Yatzy.largeStraight(6, 2, 3, 4, 5));
        assertEquals(20, Yatzy.largeStraight(2, 3, 4, 5, 6));
        assertEquals(0, Yatzy.largeStraight(1, 2, 2, 4, 5));
    }

    @Test
    public void fullHouse() {
        assertEquals(18, Yatzy.fullHouse(6, 2, 2, 2, 6));
        assertEquals(0, Yatzy.fullHouse(2, 3, 4, 5, 6));
    }
}

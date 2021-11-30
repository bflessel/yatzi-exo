package yatzyservice;

import entities.DiceSet;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;


class YatzyTest {

    @ParameterizedTest
    @CsvSource({"2, 3, 4, 5, 1, 15", "3, 3, 4, 5, 1,16"})
    void player_should_scores_sum_of_all_dice(Integer dice1, Integer dice2, Integer dice3, Integer dice4, Integer dice5, Integer result) {

        assertEquals(result, Yatzy.getChanceScore(DiceSet.of(dice1, dice2, dice3, dice4, dice5)));
    }

    @ParameterizedTest
    @CsvSource({"4, 4, 4, 4, 4, 50", "6, 6, 6, 6, 6,50"})
    void player_should_score_50_when_yatzy_with_all_five_dice_equals(Integer dice1, Integer dice2, Integer dice3, Integer dice4, Integer dice5, Integer result) {
        assertEquals(result, Yatzy.getYatzyScore(DiceSet.of(dice1, dice2, dice3, dice4, dice5)));
    }

    @ParameterizedTest
    @CsvSource({"4, 4, 4, 0, 4, 0", "6, 6, 6, 6, 3,0"})
    void player_should_score_0_when_yatzy_with_not_all_five_dice_equals(Integer dice1, Integer dice2, Integer dice3, Integer dice4, Integer dice5, Integer result) {
        assertEquals(result, Yatzy.getYatzyScore(DiceSet.of(dice1, dice2, dice3, dice4, dice5)));
    }

    @ParameterizedTest
    @CsvSource({"1, 2, 3, 4, 5,1,1", "1, 2, 1, 4, 5,2,1", "6, 2, 2, 4, 5,0,1", "1, 2, 1, 1, 1,4,1"})
    void player_should_score_one_points_by_each_dice_with_DiceOccuences_at_1(Integer dice1, Integer dice2, Integer dice3, Integer dice4, Integer dice5, Integer result, Integer diceNumber) {
        assertEquals(result, Yatzy.calculateNumbersScore(DiceSet.of(dice1, dice2, dice3, dice4, dice5), diceNumber).intValue());
    }

    @ParameterizedTest
    @CsvSource({"1, 2, 3, 2, 6,4,2", "2, 2, 2, 2, 2,10,2", "6, 5, 3, 1, 4,0,2"})
    void player_should_score_one_points_by_each_dice_with_DiceOccuences_at_2(Integer dice1, Integer dice2, Integer dice3, Integer dice4, Integer dice5, Integer result, Integer diceNumber) {
        assertEquals(result, Yatzy.calculateNumbersScore(DiceSet.of(dice1, dice2, dice3, dice4, dice5), diceNumber).intValue());
    }

    @ParameterizedTest
    @CsvSource({"1, 2, 3, 2, 3,6,3", "2, 3, 3, 3, 3,12,3", "6, 5, 2, 1, 4,0,3"})
    void player_should_score_one_points_by_each_dice_with_DiceOccuences_at_3(Integer dice1, Integer dice2, Integer dice3, Integer dice4, Integer dice5, Integer result, Integer diceNumber) {
        assertEquals(result, Yatzy.calculateNumbersScore(DiceSet.of(dice1, dice2, dice3, dice4, dice5), diceNumber).intValue());
    }

    @ParameterizedTest
    @CsvSource({"4, 4, 4, 5, 5,12,4", "4, 4, 5, 5, 5,8,4", "4, 5, 5, 5, 5,4,4", "6, 5, 3, 1, 2,0,4"})
    void player_should_score_one_points_by_each_dice_with_DiceOccuences_at_4(Integer dice1, Integer dice2, Integer dice3, Integer dice4, Integer dice5, Integer result, Integer diceNumber) {
        assertEquals(result, Yatzy.calculateNumbersScore(DiceSet.of(dice1, dice2, dice3, dice4, dice5), diceNumber).intValue());
    }

    @ParameterizedTest
    @CsvSource({"4, 4, 4, 5, 5,10,5", "4, 4, 5, 5, 5,15,5", "4, 5, 5, 5, 5,20,5", "4, 4, 3, 1, 2,0,5"})
    void player_should_score_one_points_by_each_dice_with_DiceOccuences_at_5(Integer dice1, Integer dice2, Integer dice3, Integer dice4, Integer dice5, Integer result, Integer diceNumber) {
        assertEquals(result, Yatzy.calculateNumbersScore(DiceSet.of(dice1, dice2, dice3, dice4, dice5), diceNumber).intValue());
    }

    @ParameterizedTest
    @CsvSource({"4, 4, 4, 5, 5,0,6", "4, 4, 6, 5, 5,6,6", "6, 5, 6, 6, 5,18,6"})
    void player_should_score_one_points_by_each_dice_with_DiceOccuences_at_6(Integer dice1, Integer dice2, Integer dice3, Integer dice4, Integer dice5, Integer result, Integer diceNumber) {
        assertEquals(result, Yatzy.calculateNumbersScore(DiceSet.of(dice1, dice2, dice3, dice4, dice5), diceNumber).intValue());
    }

    @ParameterizedTest
    @CsvSource({"3, 4, 3, 5, 6,6", "5, 3, 3, 3, 5,10", "5, 3, 6, 6, 5,12", "1, 2, 3, 4, 5,0"})
    void player_should_score_higher_pair_value_when_calculate__pair_score(Integer dice1, Integer dice2, Integer dice3, Integer dice4, Integer dice5, Integer result) {
        assertEquals(result, Yatzy.calculatePairScore(DiceSet.of(dice1, dice2, dice3, dice4, dice5)).intValue());
    }

    @ParameterizedTest
    @CsvSource({"3, 3, 5, 4, 5,16", "3, 3, 5, 5, 5,16", "1, 2, 3, 4, 5,0"})
    void player_should_score_all_pair_value_when_calculate_double_pair_score(Integer dice1, Integer dice2, Integer dice3, Integer dice4, Integer dice5, Integer result) {
        assertEquals(result, Yatzy.calculateDoublePairScore(DiceSet.of(dice1, dice2, dice3, dice4, dice5)).intValue());
    }

    @ParameterizedTest
    @CsvSource({"3, 3, 3, 4, 5,9", "5, 3, 5, 4, 5,15", "3, 3, 3, 3, 5,9", "3, 3, 3, 3, 3,9", "1, 2, 3, 4, 5,0"})
    void player_should_score_three_same_dice_value_score_when_calculate_Three_of_a_kind_Score(Integer dice1, Integer dice2, Integer dice3, Integer dice4, Integer dice5, Integer result) {
        assertEquals(result, Yatzy.calculateThreeOfAKindScore(DiceSet.of(dice1, dice2, dice3, dice4, dice5)).intValue());
    }

    @ParameterizedTest
    @CsvSource({"3, 3, 3, 3, 5,12", "5, 5, 5, 4, 5,20", "1, 2, 3, 4, 5,0"})
    void player_should_score_four_same_dice_value_score_when_calculate_Four_of_a_kind_Score(Integer dice1, Integer dice2, Integer dice3, Integer dice4, Integer dice5, Integer result) {
        assertEquals(result, Yatzy.calculateFourOfAKindScore(DiceSet.of(dice1, dice2, dice3, dice4, dice5)).intValue());
    }

    @ParameterizedTest
    @CsvSource({"1, 2, 3, 4, 5,15", "2, 3, 4, 5, 1,15", "1, 2, 2, 4, 5,0", "1, 2, 6, 4, 5,0"})
    void player_should_calculate_small_straight_score(Integer dice1, Integer dice2, Integer dice3, Integer dice4, Integer dice5, Integer result) {
        assertEquals(result, Yatzy.calculateSmallStraightScore(DiceSet.of(dice1, dice2, dice3, dice4, dice5)).intValue());
    }

    @ParameterizedTest
    @CsvSource({"6, 2, 3, 4, 5,20", "2, 3, 4, 5, 6,20", "1, 2, 2, 4, 5,0", "1, 2, 6, 4, 5,0"})
    void player_should_calculate_large_straight_score(Integer dice1, Integer dice2, Integer dice3, Integer dice4, Integer dice5, Integer result) {
        assertEquals(result, Yatzy.calculateLargeStraightScore(DiceSet.of(dice1, dice2, dice3, dice4, dice5)).intValue());
    }

    @ParameterizedTest
    @CsvSource({"6, 2, 2, 2, 6,18", "2, 3, 4, 5, 6,0", "2, 2, 6, 5, 6,0"})
    void player_should_calculate_full_house_score(Integer dice1, Integer dice2, Integer dice3, Integer dice4, Integer dice5, Integer result) {
        assertEquals(result, Yatzy.calculateFullHouseScore(DiceSet.of(dice1, dice2, dice3, dice4, dice5)).intValue());
    }
}

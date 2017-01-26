package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given an array of scores that are non-negative integers. Player 1 picks one
 * of the numbers from either end of the array followed by the player 2 and then
 * player 1 and so on. Each time a player picks a number, that number will not
 * be available for the next player. This continues until all the scores have
 * been chosen. The player with the maximum score wins. 
 * 
 * Given an array of scores, predict whether player 1 is the winner. You can assume
 * each player plays to maximize his score. 
 * 
 * Example 1:
 * 
 * Input: [1, 5, 2]
 * Output: False
 * Explanation: Initially, player 1 can choose between 1 and 2. If he chooses
 * 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses
 * 5, then player 1 will be left with 1 (or 2). So, final score of player 1 is
 * 1 + 2 = 3, and player 2 is 5. Hence, player 1 will never be the winner and
 * you need to return False.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: [1, 5, 233, 7]
 * Output: True
 * Explanation: Player 1 first chooses 1. Then player 2 have to choose between
 * 5 and 7. No matter which number player 2 choose, player 1 can choose 233.Finally,
 * player 1 has more score (234) than player 2 (12), so you need to return True
 * representing player1 can win.
 * 
 * 
 * 
 * Note:
 * 
 * 1 <= length of the array <= 20. 
 * Any scores in the given array are non-negative integers and will not exceed
 * 10,000,000.
 * If the scores of both players are equal, then player 1 is still the winner.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/predict-the-winner/">Predict The Winner</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum PredictTheWinner {

  RECURSIVE_SOLUTION {

    @Override
    public boolean solve(int[] nums) {
      return recur(nums, 0, nums.length - 1) >= 0;
    }

    int recur(int[] nums, int start, int end) {
      return start == end ? nums[end]
          : Math.max(nums[end] - recur(nums, start, end - 1),
              nums[start] - recur(nums, start + 1, end));
    }

  },

  BOTTOM_UP_METHOD {

    @Override
    public boolean solve(int[] nums) {
      // TODO dp again.
      return false;
    }

  };

  public abstract boolean solve(int[] nums);

  public static class TestPredictTheWinner {

    int[] nums = {1, 5, 233, 7};
    boolean expected = true;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, RECURSIVE_SOLUTION.solve(nums));
    }

  }

}

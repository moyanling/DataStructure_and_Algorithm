package org.mo39.fmbh.algorithm.math;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.S;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a non-empty integer array, find the minimum number of moves required
 * to make all array elements equal, where a move is incrementing a selected element
 * by 1 or decrementing a selected element by 1.
 * 
 * You may assume the array's length is at most 10,000.
 * 
 * Example:
 * 
 * Input:
 * [1,2,3]
 * 
 * Output:
 * 2
 * 
 * Explanation:
 * Only two moves are needed (remember each move increments or decrements one
 * element):
 * 
 * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/">Minimum
 *      Moves To Equal Array Elements II</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum MinimumMovesToEqualArrayElementsII {

  /**
   * Using element in the array as the moving target will always require a smaller move than using
   * element outside the array.
   */
  BRUTE_FORCE {

    @Override
    public int solve(int[] nums) {
      double min = Double.MAX_VALUE;
      for (int i = 0; i < nums.length; i++) {
        double sum = 0;
        for (int j = 0; j < nums.length; j++) {
          sum += Math.abs(nums[j] - nums[i]);
        }
        min = Math.min(min, sum);
      }
      return (int) min;
    }

  },

  /**
   * This is just following the definition of Median.
   */
  SORT_SOLUTION_0 {

    @Override
    public int solve(int[] nums) {
      Arrays.sort(nums);
      int moves = 0;
      for (int i = 0; i < nums.length; i++) {
        moves += Math.abs(nums[i] - nums[nums.length / 2]);
      }
      return moves;
    }

  },

  /**
   * A trick to reduce half of the calculation.
   */
  SORT_SOLUTION_1 {

    @Override
    public int solve(int[] nums) {
      Arrays.sort(nums);
      int moves = 0, i = -1, j = nums.length;
      while (++i < --j) {
        moves += nums[j] - nums[i];
      }
      return moves;
    }

  },

  SOLUTION {

    @Override
    public int solve(int[] nums) {
      int median = S.medianOf(nums), moves = 0;
      for (int i = 0; i < nums.length; i++) {
        moves += Math.abs(nums[i] - median);
      }
      return moves;
    }

  };

  public abstract int solve(int[] nums);

  public static class TestMinimumMovesToEqualArrayElementsII {

    private int[] nums = {1, 2, 3};
    private int expected = 2;

    @Test
    public void testBruteForce() {
      Assert.assertEquals(expected, BRUTE_FORCE.solve(nums));
    }

    @Test
    public void testSortSolution0() {
      Assert.assertEquals(expected, SORT_SOLUTION_0.solve(nums));
    }

    @Test
    public void testSortSolution1() {
      Assert.assertEquals(expected, SORT_SOLUTION_1.solve(nums));
    }

    @Test
    public void testSolution() {
      Assert.assertEquals(expected, SOLUTION.solve(nums));
    }

  }

}

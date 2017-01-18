package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given an integer array with all positive numbers and no duplicates, find the number of possible
 * combinations that add up to a positive integer target.
 * 
 * Example:
 * 
 * nums = [1, 2, 3] target = 4
 * 
 * The possible combination ways are: (1, 1, 1, 1) (1, 1, 2) (1, 2, 1) (1, 3) (2, 1, 1) (2, 2) (3,
 * 1)
 * 
 * Note that different sequences are counted as different combinations.
 * 
 * Therefore the output is 7.
 * 
 * 
 * 
 * Follow up: What if negative numbers are allowed in the given array? How does it change the
 * problem? What limitation we need to add to the question to allow negative numbers?
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/combination-sum-iv/">Combination Sum IV</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum CombinationSumIV {

  RECURSIVE_SOLUTION {

    @Override
    public int solve(int[] nums, int target) {
      if (target == 0) return 1;
      if (target < 0) return 0;
      int count = 0;
      for (int i = 0; i < nums.length; i++) {
        count += solve(nums, target - nums[i]);
      }
      return count;
    }

  },

  TOP_DOWN_WITH_MEMO {

    private Map<Integer, Integer> memo;

    @Override
    public int solve(int[] nums, int target) {
      memo = new HashMap<>();
      return recur(nums, target);
    }

    public int recur(int[] nums, int target) {
      if (target == 0) return 1;
      int count = 0;
      for (int i = 0; i < nums.length; i++) {
        if (target >= nums[i]) {
          count += memo.getOrDefault(target - nums[i], recur(nums, target - nums[i]));
        }
      }
      memo.put(target, count);
      return count;
    }

  },

  /**
   * //TODO
   */
  BOTTOM_UP_METHOD {

    @Override
    public int solve(int[] nums, int target) {
      int[] memo = new int[target + 1];
      memo[0] = 1;
      for (int i = 1; i < memo.length; i++) {
        for (int j = 0; j < nums.length; j++) {
          if (i - nums[j] >= 0) {
            memo[i] += memo[i - nums[j]];
          }
        }
      }
      return memo[target];
    }

  };

  public abstract int solve(int[] nums, int target);

  public static class TestCombinationSumIV {

    private int[] nums = {1, 2, 3};
    private int target = 32;
    private int expected = 181997601;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, RECURSIVE_SOLUTION.solve(nums, target));
      Assert.assertEquals(expected, TOP_DOWN_WITH_MEMO.solve(nums, target));
      Assert.assertEquals(expected, BOTTOM_UP_METHOD.solve(nums, target));
    }

  }

}

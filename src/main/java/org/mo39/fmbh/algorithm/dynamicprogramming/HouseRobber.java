package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Test;
import org.mo39.fmbh.common.Z;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * @see <a href="https://leetcode.com/problems/house-robber/">House Robber</a>
 * 
 * @author Jihan Chen
 *
 */
@ProblemSource(LEETCODE)
public enum HouseRobber {

  /**
   * Time complexity is <b>O(2^n)</b> and cannot pass all test cases in leetcode.
   * 
   */
  RECURSIVE_SOLUTION {

    private int[] nums;

    @Override
    public int solve(int[] nums) {
      this.nums = nums;
      return recur(0, nums.length - 1);
    }

    public int recur(int i, int j) {
      if (j < i) return 0;
      if (j == i) return nums[i];
      if (i + 1 == j) return Math.max(nums[i], nums[j]);
      return Math.max(recur(i, j - 2) + nums[j], recur(i, j - 3) + nums[j - 1]);
    }

  },

  /**
   * Although the constant coefficiency is high, it's sufficient to pass all test cases on LeetCode.<br>
   * Time Complexity is 
   * 
   */
  BOTTOM_UP_WITH_MEMO {

    private int[] nums;
    private Integer[][] memo;

    @Override
    public int solve(int[] nums) {
      this.nums = nums;
      this.memo = new Integer[nums.length][nums.length];
      return recur(0, nums.length - 1);
    }

    public int recur(int i, int j) {
      if (j < i) return 0;
      if (j == i) return nums[i];
      if (i + 1 == j) return Math.max(nums[i], nums[j]);
      if (memo[i][j] != null) return memo[i][j];
      memo[i][j] = Math.max(recur(i, j - 2) + nums[j], recur(i, j - 3) + nums[j - 1]);
      return memo[i][j];
    }

  };

  public abstract int solve(int[] nums);

  public static class TestHouseRobber {

    private int[] nums = {3, 8, 2, 3, 9, 10, 5};

    @Test
    public void testRecursiveSolution() {
      Z.print(HouseRobber.RECURSIVE_SOLUTION.solve(nums));
    }

    @Test
    public void testBottomUpWithMemo() {
      Z.print(HouseRobber.BOTTOM_UP_WITH_MEMO.solve(nums));
    }

  }

}

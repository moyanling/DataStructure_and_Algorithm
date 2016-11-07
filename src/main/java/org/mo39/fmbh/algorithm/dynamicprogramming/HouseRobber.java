package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
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
   * Time complexity is <b>O(2^n)</b> and cannot pass all test cases in leetcode due to Time Limit.
   * Recurrence Formula:<br>
   *
   * <pre>
   *                | 0                                     if i<0
   * maxOf[0, i] =  | nums[0]                               if i=0
   *                | Math.max(nums[0], nums[i])            if i=1
   *                | Math.max(maxOf[0,i-2]+nums[i], maxOf[0,i-3]+nums[i-1])       o.w.
   * </pre>
   */
  RECURSIVE_SOLUTION {

    private int[] nums;

    @Override
    public int solve(int[] nums) {
      this.nums = nums;
      return recur(nums.length - 1);
    }

    public int recur(int i) {
      if (i < 0) return 0;
      if (i == 0) return nums[0];
      if (i == 1) return Math.max(nums[0], nums[i]);
      return Math.max(recur(i - 2) + nums[i], recur(i - 3) + nums[i - 1]);
    }

  },

  /**
   * Although the constant coefficiency is high, it's sufficient to pass all test cases on LeetCode.
   * <br>
   * Time Complexity is //TODO
   *
   */
  TOP_DOWN_WITH_MEMO {

    private int[] nums;
    private Integer[] memo;

    @Override
    public int solve(int[] nums) {
      this.nums = nums;
      memo = new Integer[nums.length];
      int result = recur(nums.length - 1);
      Z.print(memo);
       return result;
    }

    public int recur(int i) {
      if (i < 0) return 0;
      if (i == 0) return nums[0];
      if (0 + 1 == i) return Math.max(nums[0], nums[i]);
      if (memo[i] != null) return memo[i];
      memo[i] = Math.max(recur(i - 2) + nums[i], recur(i - 3) + nums[i - 1]);
      return memo[i];
    }

  },

  BOTTOM_UP_METHOD {

    private int[] memo;

    @Override
    public int solve(int[] nums) {
      if (nums.length == 1) return nums[0];
      if (nums.length == 2) return Math.max(nums[0], nums[1]);
      memo = new int[nums.length];
      for (int i = 0; i < nums.length; i++) {
        if (i == 0) memo[0] = nums[0];

      }

      // TODO Auto-generated method stub
      return 0;
    }

  };

  public abstract int solve(int[] nums);

  public static class TestHouseRobber {

    private int[] nums = {3, 8, 2, 3, 9, 10, 5};
    private int expected = 22;

    @Test
    public void testRecursiveSolution() {
      Assert.assertEquals(expected, HouseRobber.RECURSIVE_SOLUTION.solve(nums));
    }

    @Test
    public void testBottomUpWithMemo() {
      Assert.assertEquals(expected, HouseRobber.TOP_DOWN_WITH_MEMO.solve(nums));
    }

  }

}

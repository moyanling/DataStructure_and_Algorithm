package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain
 * amount of money stashed, the only constraint stopping you from robbing each of them is that
 * adjacent houses have security system connected and it will automatically contact the police if
 * two adjacent houses were broken into on the same night.<br/>
 * Given a list of non-negative integers representing the amount of money of each house, determine
 * the maximum amount of money you can rob tonight without alerting the police.<br/>
 * 
 * @see <a href="https://leetcode.com/problems/house-robber/">House Robber</a>
 * @author Jihan Chen
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

  /**
   * dp[i][1] means rob the current house and dp[i][0] means don't.//TODO
   */
  BOTTOM_UP_METHOD_0 {

    @Override
    public int solve(int[] nums) {
      int[][] dp = new int[nums.length + 1][2];
      for (int i = 1; i <= nums.length; i++) {
        dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
        dp[i][1] = nums[i - 1] + dp[i - 1][0];
      }
      return Math.max(dp[nums.length][0], dp[nums.length][1]);
    }

  },


  /**
   * The solution of this problem yields the fact that, the current solution only depends on one or
   * two previous solutions. So the scale is //TODO. That's why this can be solved with <b>O(1)</b>
   * space. The time complexity is <b>O(n)</b>;
   *
   */
  BOTTOM_UP_METHOD_1 {

    /**
     * This variable stores the sum of the previous houses and can add nums[i] because it does not
     * have nums[i-1] in the sum;
     */
    private int toAdd = 0;

    /**
     * This variable stores the sum of the previous houses and can not add nums[i] because it has
     * nums[i-1] in the sum;
     */
    private int cantAdd = 0;

    @Override
    public int solve(int[] nums) {
      for (int n : nums) {
        toAdd += n;
        /**
         * If we rob current but total money is still smaller than the one not rob, then why bother
         * rob it? This makes us lose the chance to rob next house. So make it the same as cantAdd.
         */
        if (toAdd < cantAdd) toAdd = cantAdd;
        /**
         * Otherwise, since we add the current house to toAdd, it can not add next house next time,
         * so swap it with cantAdd.
         */
        else {
          int temp = toAdd;
          toAdd = cantAdd;
          cantAdd = temp;
        }
      }
      return Math.max(toAdd, cantAdd);
    }

  },

  /**
   * //TODO
   */
  BOTTOM_UP_METHOD_2 {

    @Override
    public int solve(int[] nums) {
      int prevNo = 0;
      int prevYes = 0;
      for (int n : nums) {
        int temp = prevNo;
        prevNo = Math.max(prevNo, prevYes);
        prevYes = n + temp;
      }
      return Math.max(prevNo, prevYes);
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
    public void testTopDownWithMemo() {
      Assert.assertEquals(expected, HouseRobber.TOP_DOWN_WITH_MEMO.solve(nums));
    }

    @Test
    public void testBottomUpMethod() {
      Assert.assertEquals(expected, HouseRobber.BOTTOM_UP_METHOD_0.solve(nums));
      Assert.assertEquals(expected, HouseRobber.BOTTOM_UP_METHOD_1.solve(nums));
      Assert.assertEquals(expected, HouseRobber.BOTTOM_UP_METHOD_2.solve(nums));
    }

  }

}

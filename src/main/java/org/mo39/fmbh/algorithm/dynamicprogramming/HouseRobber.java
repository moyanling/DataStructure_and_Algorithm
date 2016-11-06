package org.mo39.fmbh.algorithm.dynamicprogramming;

import org.junit.Test;
import org.mo39.fmbh.common.Z;

/**
 * @see <a href="https://leetcode.com/problems/house-robber/">House Robber</a>
 * 
 * @author Jihan Chen
 *
 */
public enum HouseRobber {

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

  };

  public abstract int solve(int[] nums);

  public static class TestHouseRobber {

    private int[] nums = {3, 8, 2, 3, 9, 10, 5};

    @Test
    public void testRecursiveSolution() {
      Z.print(HouseRobber.RECURSIVE_SOLUTION.solve(nums));

    }

  }

}

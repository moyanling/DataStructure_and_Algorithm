package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Arrays;

import org.junit.Test;
import org.mo39.fmbh.common.Z;
import org.mo39.fmbh.common.annotation.ProblemSource;

@ProblemSource(LEETCODE)
public enum LongestIncreasingSubsequence {

  RECURSIVE_SOLUTION {


    @Override
    public int solve(int[] nums) {
      if (nums.length == 1) return 1;
      return recur(nums, nums.length - 2, nums[nums.length - 1]) + 1;
    }

    private int recur(int[] nums, int end, int lastValue) {
      if (end < 0) return 0;
      int len1 = 0, len2 = 0;
      if (nums[end] < lastValue) {
        Z.print("end    " + end + " : " + nums[end]);
        len1 = 1 + recur(nums, end - 1, nums[end]);
      } else {
        len1 = recur(nums, end - 2, lastValue);
      }
      int len3 = recur(nums, end - 1, nums[end]);
      if (lastValue == 10) {
        Z.print("!!!!!!!!!!!!!!!!!!!!!!!!!");
      }
      // int[] newNums = Arrays.copyOf(nums, nums.length);
      // Z.swap(newNums, end, end + 1);
      // len2 = recur(newNums, end - 1, newNums[end]);
      return Math.max(Math.max(len1, len2), len3);
      // return Math.max(len1, len2);
    }

  },

  TOP_DOWN_WITH_MEMO {

    int[] memo;

    @Override
    public int solve(int[] nums) {
      if (nums.length == 1) return 1;
      memo = new int[nums.length];
      int result = recur(nums, nums.length - 1);
      return result == 0 ? 0 : result + 1;
    }

    private int recur(int[] nums, int end) {
      if (end <= 0) return 0;
      int len1 = 0, len2 = 0;
      if (nums[end] > nums[end - 1]) {
        len1 = 1 + recur(nums, end - 1);
      }
      int[] newNums = Arrays.copyOf(nums, nums.length);
      Z.swap(newNums, end, end - 1);
      len2 = recur(newNums, end - 1);
      memo[end] = Math.max(len1, len2);
      return memo[end];
    }

  };

  public abstract int solve(int[] nums);

  public static class TestLongestIncreasingSubsequence {

    private int[] nums = new int[] {10, 9, 2, 5, 3, 7, 101, 18};
    private int expected = 4;

    @Test
    public void testSolutions() {
      // Assert.assertEquals(expected, RECURSIVE_SOLUTION.solve(nums));
      // Assert.assertEquals(expected, TOP_DOWN_WITH_MEMO.solve(nums));
      Z.print(RECURSIVE_SOLUTION.solve(new int[] {1, 3, 6, 7, 9, 4, 10, 5, 6}));
      Z.print(RECURSIVE_SOLUTION.solve(nums));
    }

  }

}

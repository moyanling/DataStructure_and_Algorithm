package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k.
 * If there isn't one, return 0 instead.
 * 
 *     Note:
 * The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
 * 
 * 
 * 
 *     Example 1:
 * 
 * 
 * Given nums = [1, -1, 5, -2, 3], k = 3,
 * return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
 * 
 * 
 * 
 *     Example 2:
 * 
 * 
 * Given nums = [-2, -1, 2, 1], k = 1,
 * return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
 * 
 * 
 * Follow Up: Can you do it in O(n) time?
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/">Maximum Size
 *      Subarray Sum Equals K</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum MaximumSizeSubarraySumEqualsK {

  RECURSIVE_SOLUTION {

    @Override
    public int solve(int[] nums, int k) {
      int sum = 0;
      for (int i = 0; i < nums.length; i++) {
        sum += nums[i];
      }
      return recur(nums, 0, nums.length - 1, sum, k);
    }

    private int recur(int[] nums, int start, int end, int sum, int k) {
      if (sum == k) return end - start + 1;
      if (start == end) return 0;
      int useStart = recur(nums, start, end - 1, sum - nums[end], k);
      int useEnd = recur(nums, start + 1, end, sum - nums[start], k);
      return Math.max(useStart, useEnd);
    }

  },

  BOTTOM_UP_METHOD {

    private int[][] memo;

    @Override
    public int solve(int[] nums, int k) {
      memo = new int[nums.length][nums.length];
      int sum = 0;
      for (int i = 0; i < nums.length; i++) {
        sum += nums[i];
      }
      return recur(nums, 0, nums.length - 1, sum, k);
    }

    private int recur(int[] nums, int start, int end, int sum, int k) {
      if (sum == k) return end - start + 1;
      if (start == end) return 0;
      int useStart = tryMemo(nums, start, end - 1, sum - nums[end], k);
      int useEnd = tryMemo(nums, start + 1, end, sum - nums[start], k);
      return Math.max(useStart, useEnd);
    }

    private int tryMemo(int[] nums, int start, int end, int sum, int k) {
      if (memo[start][end] != 0) return memo[start][end];
      return memo[start][end] = recur(nums, start, end, sum, k);
    }

  },

  /**
   * Here is a trick : since we travel from 0 to nums.length, if a sum already exist, it's index
   * must be longer than the current duplicate one.
   */
  SOLUTION {

    @Override
    public int solve(int[] nums, int k) {
      int sum = 0, max = 0;
      HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
      for (int i = 0; i < nums.length; i++) {
        sum = sum + nums[i];
        if (sum == k) max = i + 1;
        else if (map.containsKey(sum - k)) max = Math.max(max, i - map.get(sum - k));
        if (!map.containsKey(sum)) map.put(sum, i);
      }
      return max;
    }

  };

  public abstract int solve(int[] nums, int k);

  public static class TestMaximumSizeSubarraySumEqualsK {

    private int[] nums = {1, -1, 5, -2, 3};
    private int k = 3;
    private int expected = 4;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, RECURSIVE_SOLUTION.solve(nums, k));
      Assert.assertEquals(expected, BOTTOM_UP_METHOD.solve(nums, k));
    }

  }

}

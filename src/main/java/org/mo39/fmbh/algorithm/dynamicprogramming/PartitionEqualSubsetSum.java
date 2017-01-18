package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a non-empty array containing only positive integers, find if the array
 * can be partitioned into two subsets such that the sum of elements in both subsets
 * is equal.
 * 
 * 
 * Note:
 * 
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: [1, 5, 11, 5]
 * 
 * Output: true
 * 
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: [1, 2, 3, 5]
 * 
 * Output: false
 * 
 * Explanation: The array cannot be partitioned into equal sum subsets.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/partition-equal-subset-sum/">Partition Equal Subset
 *      Sum</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum PartitionEqualSubsetSum {

  BACK_TRACKING {

    @Override
    public boolean solve(int[] nums) {
      int sum = 0;
      for (int n : nums) {
        sum += n;
      }
      if (sum % 2 != 0) return false;
      return recur(nums, 0, sum / 2);
    }

    private boolean recur(int[] nums, int i, int sum) {
      if (i >= nums.length || sum < 0) {
        return false;
      } else if (sum == 0) return true;
      return recur(nums, i + 1, sum - nums[i]) | recur(nums, i + 1, sum);
    }

  },

  /**
   * //TODO urgent
   */
  BOTTOM_UP_METHOD {

    @Override
    public boolean solve(int[] nums) {
      int sum = 0;
      for (int n : nums) {
        sum += n;
      }
      if (sum % 2 != 0) return false;
      boolean[] memo = new boolean[sum / 2 + 1];
      memo[0] = true;
      for (int n : nums) {
        for (int j = sum / 2; j >= n; j--) {
          memo[j] |= memo[j - n];
        }
      }
      return memo[sum / 2];
    }

  };

  public abstract boolean solve(int[] nums);

  public static class TestPartitionEqualSubsetSum {

    private int[] nums = {1, 5, 11, 5};
    private boolean expected = true;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, BACK_TRACKING.solve(nums));
      Assert.assertEquals(expected, BOTTOM_UP_METHOD.solve(nums));
    }
  }

}

package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0
 * <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
 * 
 * For example, given nums = [-2, 0, 1, 3], and target = 2.
 * 
 * Return 2. Because there are two triplets which sums are less than 2:
 * 
 * [-2, 0, 1] [-2, 0, 3]
 * 
 * 
 * Follow up: Could you solve it in O(n2) runtime?
 * 
 * </pre>
 * 
 * 
 * @see <a href="https://leetcode.com/problems/three-sum-smaller/">Three Sum Smaller</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum ThreeSumSmaller {

  BRUTE_FORCE {

    @Override
    public int solve(int[] nums, int target) {
      int count = 0;
      for (int i = 0; i < nums.length - 2; i++) {
        for (int j = i + 1; j < nums.length - 1; j++) {
          for (int k = j + 1; k < nums.length; k++) {
            if (nums[i] + nums[j] + nums[k] < target) count++;
          }
        }
      }
      return count;
    }

  },

  SOLUTION {

    @Override
    public int solve(int[] nums, int target) {
      int count = 0;
      Arrays.sort(nums);
      for (int i = 0; i < nums.length - 2; i++) {
        int left = i + 1, right = nums.length - 1;
        while (left < right) {
          if (nums[i] + nums[left] + nums[right] >= target) right--;
          else {
            count += right - left;
            left++;
          }
        }
      }
      return count;
    }

  };

  public abstract int solve(int[] nums, int target);

  public static class TestThreeSumSmaller {

    private int[] nums = {-2, 0, 1, 3};
    private int target = 2;
    private int expected = 2;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, BRUTE_FORCE.solve(nums, target));
      Assert.assertEquals(expected, SOLUTION.solve(nums, target));
    }

  }

}

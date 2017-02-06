package org.mo39.fmbh.datastructure.twopointers;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Arrays;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given
 * number, target. Return the sum of the three integers. You may assume that each input would have
 * exactly one solution.
 * 
 * 
 * For example, given array S = {-1 2 1 -4}, and target = 1.
 * 
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/3sum-closest/">Three Sum Closest</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum ThreeSumClosest {

  SOLUTION {

    @Override
    public int solve(int[] nums, int target) {
      Arrays.sort(nums);
      Integer result = null;
      for (int i = 0; i < nums.length - 2; i++) {
        int start = i + 1, end = nums.length - 1;
        while (start < end) {
          int sum = nums[i] + nums[start] + nums[end];
          if (sum == target) return sum;
          if (result == null || Math.abs(target - sum) < Math.abs(target - result)) result = sum;
          if (sum < target) start++;
          else end--;
        }
      }
      return result;
    }

  };

  public abstract int solve(int[] nums, int target);

}

package org.mo39.fmbh.datastructure.array;

import org.mo39.fmbh.common.annotation.ProblemSource;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

/**
 *
 *
 * <pre>
 * Given an unsorted array of integers, find the length of longest continuous increasing subsequence.
 *
 * Example 1:
 * Input: [1,3,5,4,7]
 * Output: 3
 * Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3.
 * Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4.
 * Example 2:
 * Input: [2,2,2,2,2]
 * Output: 1
 * Explanation: The longest continuous increasing subsequence is [2], its length is 1.
 * Note: Length of the array will not exceed 10,000.
 * </pre>
 *
 * @see <a href="https://leetcode.com/problems/longest-continuous-increasing-subsequence/">Longest
 *     Continuous Increasing Subsequence</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum LongestContinuousIncreasingSubsequence {
  SOLUTION {
    @Override
    public int findLengthOfLCIS(int[] nums) {
      if (nums == null || nums.length == 0) return 0;
      int max = 1, len = 1;
      for (int i = 1; i < nums.length; i++) {
        if (nums[i] > nums[i - 1]) len++;
        else len = 1;
        max = Math.max(max, len);
      }
      return max;
    }
  };

  public abstract int findLengthOfLCIS(int[] nums);
}

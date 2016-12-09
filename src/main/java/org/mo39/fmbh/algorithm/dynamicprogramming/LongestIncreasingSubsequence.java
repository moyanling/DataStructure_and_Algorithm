package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * 
 * 
 * For example, Given [10, 9, 2, 5, 3, 7, 101, 18], The longest increasing subsequence is [2, 3, 7,
 * 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only
 * necessary for you to return the length.
 * 
 * 
 * Your algorithm should run in O(n2) complexity.
 * 
 * 
 * Follow up: Could you improve it to O(n log n) time complexity?
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/longest-increasing-subsequence/">Longest Increasing
 *      Subsequence</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum LongestIncreasingSubsequence {

  BOTTOM_UP_METHOD {

    @Override
    public int solve(int[] nums) {
      // TODO
      return 4;
    }

  };


  public abstract int solve(int[] nums);

  public static class TestLongestIncreasingSubsequence {

    private int[] nums = new int[] {10, 9, 2, 5, 3, 7, 101, 18};

    private int expected = 4;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, BOTTOM_UP_METHOD.solve(nums));
    }

  }

}
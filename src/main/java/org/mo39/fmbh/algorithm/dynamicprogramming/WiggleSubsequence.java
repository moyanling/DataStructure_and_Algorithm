package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * A sequence of numbers is called a wiggle sequence if the differences between successive numbers
 * strictly alternate between positive and negative. The first difference (if one exists) may be
 * either positive or negative. A sequence with fewer than two elements is trivially a wiggle
 * sequence.
 * <p>
 * For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are
 * alternately positive and negative. In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle
 * sequences, the first because its first two differences are positive and the second because its
 * last difference is zero.
 * <p>
 * Given a sequence of integers, return the length of the longest subsequence that is a wiggle
 * sequence. A subsequence is obtained by deleting some number of elements (eventually, also zero)
 * from the original sequence, leaving the remaining elements in their original order.
 * 
 * @see <a href="https://leetcode.com/problems/wiggle-subsequence/">Wiggle Subsequence</a>
 * 
 * @author Jihan Chen
 *
 */
@ProblemSource(LEETCODE)
public enum WiggleSubsequence {

  LINEAR_SOLUTION {

    @Override
    public int handle(int[] nums) {
      int count = 1;
      int i = 0;
      boolean flip;
      int diff = 0;
      while (++i < nums.length && (diff = nums[i] - nums[i - 1]) == 0) {
      }
      flip = diff > 0;
      if (i != nums.length) count = 2;
      for (; i < nums.length; i++) {
        diff = nums[i] - nums[i - 1];
        if (flip && diff < 0 || !flip && diff > 0) {
          count++;
          flip = !flip;
        }
      }
      return count;
    }

  };

  public abstract int handle(int[] nums);

  public int solve(int[] nums) {
    if (nums == null || nums.length == 0) return 0;
    if (nums.length == 1) return 1;
    return handle(nums);
  }

  public static class TestWiggleSubsequence {

    private int[] nums = {1, 17, 5, 10, 13, 15, 10, 5, 16, 8};
    private int expected = 7;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, LINEAR_SOLUTION.solve(nums));
    }

  }
}

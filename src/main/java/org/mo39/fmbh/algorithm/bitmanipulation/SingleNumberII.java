package org.mo39.fmbh.algorithm.bitmanipulation;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given an array of integers, every element appears three times except for one,
 * which appears exactly once. Find that single one.
 * 
 * 
 * 
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement
 * it without using extra memory?
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/single-number-ii/">Single Number II</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum SingleNumberII {

  /**
   * A brand new idea using state machine.
   */
  SOLUTION {

    @Override
    public int solve(int[] nums) {
      int ones = 0, twos = 0;
      for (int i = 0; i < nums.length; i++) {
        ones = (ones ^ nums[i]) & ~twos;
        twos = (twos ^ nums[i]) & ~ones;
      }
      return ones;
    }

  };

  public abstract int solve(int[] nums);

  public static class TestSingleNumberII {

    private int[] A = {1, 1, 1, 9, 4, 4, 7, 7, 4, 7};
    private int expected = 9;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(A));
    }

  }

}

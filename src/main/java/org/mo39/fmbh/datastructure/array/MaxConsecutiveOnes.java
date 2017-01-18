package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a binary array, find the maximum number of consecutive 1s in this array.
 * 
 * Example 1:
 * 
 * Input: [1,1,0,1,1,1]
 * Output: 3
 * Explanation: The first two digits or the last three digits are consecutive
 * 1s.
 *     The maximum number of consecutive 1s is 3.
 * 
 * 
 * 
 * Note:
 * 
 * The input array will only contain 0 and 1.
 * The length of input array is a positive integer and will not exceed 10,000
 * </pre>
 * @see <a href="https://leetcode.com/problems/max-consecutive-ones/">Max Consecutive Ones</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum MaxConsecutiveOnes {

  SOLUTION {

    @Override
    public int solve(int[] nums) {
      int cur = 0, max = 0;
      for (int i : nums) {
        if (i == 1) max = Math.max(max, ++cur);
        else cur = 0;
      }
      return max;
    }

  };

  public abstract int solve(int[] nums);

  public static class TestMaxConsecutiveOnes {

    private int[] nums = {1, 0, 1, 1, 0, 1};
    private int expected = 2;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(nums));
    }

  }

}
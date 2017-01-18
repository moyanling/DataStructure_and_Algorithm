package org.mo39.fmbh.algorithm.slidingwindow;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;


/**
 * <pre>
 * Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at
 * most one 0.
 * 
 *  Example 1:
 * 
 * Input: [1,0,1,1,0]
 * Output: 4
 * 
 * Explanation: Flip the first zero will get the the maximum number of consecutive 1s. After
 * flipping, the maximum number of consecutive 1s is 4.
 * 
 * 
 * 
 * Note:
 * 
 * The input array will only contain 0 and 1. The length of input array is a positive integer and
 * will not exceed 10,000
 * 
 * </pre>
 * 
 * Follow up: What if the input numbers come in one by one as an infinite stream? In other words,
 * you can't store all numbers coming from the stream as it's too large to hold in memory. Could you
 * solve it efficiently?
 * 
 * @see <a href="https://leetcode.com/problems/max-consecutive-ones-ii/">Max Consecutive Ones II</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum MaxConsecutiveOnesII {

  /**
   * Loop invarient:</br>
   * 1. Count should always indicate the number of 0 in the window</br>
   * 2. The size of the window is always growing</br>
   * 3. j is exclusive and i is inclusive</br>
   * 4. Then the length is j - i if count <= 0
   */
  SOLUTION {

    @Override
    public int solve(int[] nums) {
      int i = 0, count = 0, j = 0;
      for (; i < nums.length; i++) {
        if (nums[i] == 0) count++;
        if (count > 1) {
          if (nums[j] == 0) count--;
          j++;
        }
      }
      return i - j;
    }

  };

  public abstract int solve(int[] nums);

  public static class TestMaxConsecutiveOnesII {

    private int[] nums = {1, 0, 1, 1, 0, 1};
    private int expected = 4;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(nums));
    }

  }

}

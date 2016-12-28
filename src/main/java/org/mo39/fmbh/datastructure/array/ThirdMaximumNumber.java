package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a non-empty array of integers, return the third maximum number in this
 * array. If it does not exist, return the maximum number. The time complexity
 * must be in O(n).
 * 
 * Example 1:
 * 
 * Input: [3, 2, 1]
 * 
 * Output: 1
 * 
 * Explanation: The third maximum is 1.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: [1, 2]
 * 
 * Output: 2
 * 
 * Explanation: The third maximum does not exist, so the maximum (2) is returned
 * instead.
 * 
 * 
 * 
 * Example 3:
 * 
 * Input: [2, 2, 3, 1]
 * 
 * Output: 1
 * 
 * Explanation: Note that the third maximum here means the third maximum distinct
 * number.
 * Both numbers with value 2 are both considered as second maximum.
 * </pre>
 * 
 * Assume nums is not null and contains at least 1 element.<br/>
 * This question is so straight-forward.
 * 
 * @see <a href="https://leetcode.com/problems/third-maximum-number/">Third Maximum Number</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum ThirdMaximumNumber {

  BRUTE_FORCE {

    @Override
    public int solve(int[] nums) {
      Integer[] arr = {null, null, null};
      for (int n : nums) {
        if (arr[0] == null) arr[0] = n;
        else if (n > arr[0]) {
          arr[2] = arr[1];
          arr[1] = arr[0];
          arr[0] = n;
        } else if (n < arr[0]) {
          if (arr[1] == null) arr[1] = n;
          else if (n > arr[1]) {
            arr[2] = arr[1];
            arr[1] = n;
          } else if (n < arr[1] && (arr[2] == null || n > arr[2])) arr[2] = n;
        }
      }
      return arr[2] == null ? arr[0] : arr[2];
    }

  },

  PRIORITY_QUEUE {

    @Override
    public int solve(int[] nums) {
      // TODO Auto-generated method stub
      return 0;
    }

  };

  public abstract int solve(int[] nums);

  public static class TestThirdMaximumNumber {

    private int[] nums = {1, 2, 2, 5, 3, 5};
    private int expected = 2;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, BRUTE_FORCE.solve(nums));
    }

  }



}

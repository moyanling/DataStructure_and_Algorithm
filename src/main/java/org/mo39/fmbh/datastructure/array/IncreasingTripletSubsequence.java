package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given an unsorted array return whether an increasing subsequence of length
 * 3 exists or not in the array.
 * 
 * 
 * Formally the function should:
 * Return true if there exists i, j, k  
 * such that arr[i] &lt; arr[j] &lt; arr[k] given 0 &le; i &lt; j &lt; k &le;
 * n-1 
 * else return false.
 * 
 * 
 * 
 * Your algorithm should run in O(n) time complexity and O(1) space complexity.
 * 
 * 
 * Examples:
 * Given [1, 2, 3, 4, 5],
 * return true.
 * 
 * 
 * Given [5, 4, 3, 2, 1],
 * return false.
 * 
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/increasing-triplet-subsequence/">Increasing Triplet
 *      Subsequence</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum IncreasingTripletSubsequence {

  SOLUTION {

    @Override
    public boolean solve(int[] nums) {
      if (nums.length < 3) return false;
      int firstExpected = nums[0], secondExpected = Integer.MAX_VALUE, k = 1;
      for (; k < nums.length; k++) {
        if (nums[k] > firstExpected) {
          if (nums[k] > secondExpected) return true;
          secondExpected = nums[k];
        } else firstExpected = nums[k];
      }
      return false;
    }

  };

  public abstract boolean solve(int[] nums);

  public static class TestIncreasingTripletSubsequence {

    private int[] nums = {1, 100, 4, 3, 5};
    private boolean expected = true;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(nums));
    }

  }

}

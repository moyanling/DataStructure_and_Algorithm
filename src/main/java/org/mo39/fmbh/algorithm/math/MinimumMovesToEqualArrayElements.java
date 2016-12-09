package org.mo39.fmbh.algorithm.math;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * Given a non-empty integer array of size n, find the minimum number of moves required to make all
 * array elements equal, where a move is incrementing n - 1 elements by 1.<br/>
 * Example:<br/>
 * Input:<br/>
 * [1,2,3]<br/>
 * Output:<br/>
 * 3<br/>
 * Explanation:<br/>
 * Only three moves are needed (remember each move increments two elements):<br/>
 * [1,2,3] => [2,3,3] => [3,4,3] => [4,4,4]
 * 
 * @see <a href="https://leetcode.com/problems/minimum-moves-to-equal-array-elements/">Minimum Moves
 *      To Equal Array Elements</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum MinimumMovesToEqualArrayElements {

  SOLUTION_0 {

    @Override
    public int solve(int[] nums) {
      Arrays.sort(nums);
      int toRet = 0;
      for (int i = 1; i < nums.length; i++) {
        toRet += nums[i] - nums[0];
      }
      return toRet;
    }

  },

  SOLUTION_1 {

    @Override
    public int solve(int[] nums) {
      if (nums.length <= 1) return 0;
      Integer min = null;
      int sum = 0;
      for (int i = 0; i < nums.length; i++) {
        sum += nums[i];
        if (min == null || nums[i] < min) min = nums[i];
      }
      return sum - nums.length * min;
    }

  };

  public abstract int solve(int[] nums);

  public static class TestMinimumMovesToEqualArrayElements {

    private int[] nums = {1, 2, 3};
    private int expected = 3;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION_0.solve(nums));
      Assert.assertEquals(expected, SOLUTION_1.solve(nums));
    }

  }

}

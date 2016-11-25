package org.mo39.fmbh.algorithm.math;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * You have a total of n coins that you want to form in a staircase shape, where every k-th row must
 * have exactly k coins.<br/>
 * <br/>
 * Given n, find the total number of full staircase rows that can be formed.<br/>
 * n is a non-negative integer and fits within the range of a 32-bit signed integer.<br/>
 * Example 1:<br/>
 * n = 5<br/>
 * The coins can form the following rows:<br/>
 * ¤<br/>
 * ¤ ¤<br/>
 * ¤ ¤<br/>
 * Because the 3rd row is incomplete, we return 2.<br/>
 * Example 2:<br/>
 * n = 8<br/>
 * The coins can form the following rows:<br/>
 * ¤<br/>
 * ¤ ¤<br/>
 * ¤ ¤ ¤<br/>
 * ¤ ¤<br/>
 * Because the 4th row is incomplete, we return 3.
 * 
 * @see <a href="https://leetcode.com/problems/arranging-coins/">Arranging Coins</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum ArrangingCoins {

  BRUTE_FORCE {

    @Override
    public int solve(int n) {
      int count = 1, sum = 0;
      while (sum <= n) {
        sum += count++;
      }
      return count - 2;
    }

  },

  /**
   * The <b>.0</b> matters. Otherwise there will be an Integer overflow.
   */
  MATH_SOLUTION {

    @Override
    public int solve(int n) {
      return (int) ((Math.sqrt(8.0 * n + 1) - 1) / 2);
    }

  };

  public abstract int solve(int n);

  public static class TestArrangingCoins {

    private int n = 8;
    private int expected = 3;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, BRUTE_FORCE.solve(n));
      Assert.assertEquals(expected, MATH_SOLUTION.solve(n));
    }

  }

}

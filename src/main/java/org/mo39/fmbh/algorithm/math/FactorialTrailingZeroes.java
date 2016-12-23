package org.mo39.fmbh.algorithm.math;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given an integer n, return the number of trailing zeroes in n!.
 * 
 * Note: Your solution should be in logarithmic time complexity.
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/factorial-trailing-zeroes/">Factorial Trailing
 *      Zeroes</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum FactorialTrailingZeroes {

  SOLUTION {

    @Override
    public int solve(int n) {
      return n == 0 ? 0 : n / 5 + solve(n / 5);
    }

  };

  public abstract int solve(int n);

  public static class TestFactorialTrailingZeroes {

    @Test
    public void testSolutions() {
      Assert.assertEquals(2, SOLUTION.solve(10));
    }

  }

}

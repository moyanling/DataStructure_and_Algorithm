package org.mo39.fmbh.algorithm.binarysearch;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Implement int sqrt(int x).
 * 
 * Compute and return the square root of x.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/sqrtx/">Sqrtx</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum Sqrtx {

  /**
   * Pay attention to every detail of your code. Take care of every mutiplication and addition that
   * might cause integer overflow.
   */
  SOLUTION {

    @Override
    public int solve(int x) {
      if (x == 0) return 0;
      int left = 1, right = Integer.MAX_VALUE;
      while (true) {
        int mid = left + (right - left) / 2;
        if (mid > x / mid) right = mid - 1;
        else {
          if (mid + 1 > x / (mid + 1)) return mid;
          left = mid + 1;
        }
      }
    }

  };

  public abstract int solve(int x);

  public static class TestSqrtx {

    int x = 6;
    int expected = 2;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(x));
    }

  }

}

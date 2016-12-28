package org.mo39.fmbh.algorithm.math;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 *     Given an integer, write a function to determine if it is a power of three.
 * 
 * 
 *     Follow up:
 *     Could you do it without using any loop / recursion?
 * 
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/power-of-three/">Power Of Three</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum PowerOfThree {

  BRUTE_FORCE {

    @Override
    public boolean solve(int n) {
      if (n == 0) return false;
      while (n != 1) {
        if (n % 3 != 0) return false;
        n /= 3;
      }
      return true;
    }

  },

  ONE_LINER {

    @Override
    public boolean solve(int num) {
      return Integer.toString(num, 3).matches("10*");
    }

  },

  SOLUTION {

    @Override
    public boolean solve(int n) {
      return n > 0 && 1162261467 % n == 0;
    }

  };

  public abstract boolean solve(int n);

  public static class TestPowerOfThree {

    private int isTrue = 27;
    private int isFalse = 16;

    @Test
    public void testSolutions() {
      Assert.assertTrue(BRUTE_FORCE.solve(isTrue));
      Assert.assertFalse(BRUTE_FORCE.solve(isFalse));
      // ---------
      Assert.assertTrue(SOLUTION.solve(isTrue));
      Assert.assertFalse(SOLUTION.solve(isFalse));
      // ---------
      Assert.assertTrue(ONE_LINER.solve(isTrue));
      Assert.assertFalse(ONE_LINER.solve(isFalse));
    }

  }

}

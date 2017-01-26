package org.mo39.fmbh.algorithm.bitmanipulation;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a positive integer num, write a function which returns True if num is
 * a perfect square else False.
 * 
 * 
 * Note: Do not use any built-in library function such as sqrt.
 * 
 * 
 * Example 1:
 * 
 * Input: 16
 * Returns: True
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: 14
 * Returns: False
 * 
 * 
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/valid-perfect-square/">Valid Perfect Square</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum ValidPerfectSquare {

  BRUTE_FORCE {

    @Override
    public boolean solve(int num) {
      for (int i = 1; i * i <= num; i++) {
        if (i * i == num) return true;
      }
      return false;
    }

  },

  BINARY_SEARCH {

    @Override
    public boolean solve(int num) {
      int lower = 1, upper = 46340;
      while (lower <= upper) {
        int mid = lower + upper >>> 1;
        int square = mid * mid;
        if (square == num) return true;
        else if (square < num) lower = mid + 1;
        else upper = mid - 1;
      }
      return false;
    }

  },

  /**
   * @see <a href='https://en.wikipedia.org/wiki/Integer_square_root#Using_only_integer_division'>
   *      newton method</a>
   */
  NEWTON_METHOD {

    @Override
    public boolean solve(int num) {
      long x = num;
      while (x * x > num) {
        x = x + num / x >> 1;
      }
      return x * x == num;
    }

  };

  public abstract boolean solve(int num);

  public static class TestValidPerfectSquare {

    private int num = 100;
    private boolean expected = true;

    @Test
    public void testSolutions() {
      for (ValidPerfectSquare sol : ValidPerfectSquare.values()) {
        Assert.assertEquals(expected, sol.solve(num));
      }
    }

  }

}

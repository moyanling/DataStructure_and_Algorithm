package org.mo39.fmbh.algorithm.bitmanipulation;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given an integer, write a function to determine if it is a power of two.
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/power-of-two/">Power Of Two</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum PowerOfTwo {

  BIT_MANIPULATION {

    @Override
    public boolean solve(int n) {
      if (n < 1) return false;
      int count = 0;
      for (int i = 0; i < 32; i++) {
        if (((n >>> i) & 1) == 1) count++;
      }
      return count == 1;
    }

  },

  ONE_LINER {

    @Override
    public boolean solve(int n) {
      return n > 0 && (n & (n - 1)) == 0;
    }

  };

  public abstract boolean solve(int n);

  public static class TestPowerOfTwo {

    private int isTrue = 8;
    private int isFalse = 3;

    @Test
    public void testSolutions() {
      Assert.assertTrue(BIT_MANIPULATION.solve(isTrue));
      Assert.assertTrue(ONE_LINER.solve(isTrue));
      // ---------
      Assert.assertFalse(BIT_MANIPULATION.solve(isFalse));
      Assert.assertFalse(ONE_LINER.solve(isFalse));
    }

  }

}
package org.mo39.fmbh.algorithm.bitmanipulation;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also
 * known as the Hamming weight).
 * 
 * For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011,
 * so the function should return 3.
 * 
 * @see <a href="https://leetcode.com/problems/number-of-1-bits/">Number Of 1 Bits</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum NumberOf1Bits {

  ONE_LINER_0 {

    @Override
    public int solve(int n) {
      return Long.bitCount(Integer.toUnsignedLong(n));
    }

  },

  ONE_LINER_1 {

    @Override
    public int solve(int n) {
      return n == 0 ? 0 : 1 + solve(n & (n - 1));
    }

  },

  BIT_MANIPULATION {

    @Override
    public int solve(int n) {
      int i = 0;
      while (n != 0) {
        n &= n - 1;// TODO
        i++;
      }
      return i;
    }

  };

  public abstract int solve(int n);

  public static class TestNumberOf1Bits {

    private int n = 11;
    private int expected = 3;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, ONE_LINER_0.solve(n));
      Assert.assertEquals(expected, ONE_LINER_1.solve(n));
      Assert.assertEquals(expected, BIT_MANIPULATION.solve(n));
    }

  }

}

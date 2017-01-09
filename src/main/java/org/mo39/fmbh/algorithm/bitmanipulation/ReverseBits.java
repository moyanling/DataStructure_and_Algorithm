package org.mo39.fmbh.algorithm.bitmanipulation;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Reverse bits of a given 32 bits unsigned integer.
 * 
 * For example, given input 43261596 (represented in binary as 00000010100101000001111010011100),
 * return 964176192 (represented in binary as 00111001011110000010100101000000).
 * 
 * 
 * Follow up:
 * If this function is called many times, how would you optimize it?
 * 
 * 
 * Related problem: Reverse Integer
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/reverse-bits/">Reverse Bits</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum ReverseBits {

  SOLUTION_0 {

    @Override
    public int solve(int n) {
      int toRet = 0;
      for (int i = 0; i < 32; i++) {
        toRet |= (1 << 31 - i & n) >>> 31 - i << i;
      }
      return toRet;
    }

  },

  SOLUTION_1 {

    @Override
    public int solve(int n) {
      int toRet = 0;
      for (int i = 0; i < 32; i++) {
        int len = 31 - 2 * i;
        if (len >= 0) toRet |= (1 << 31 - i & n) >>> len;
        else toRet |= (1 << 31 - i & n) << -len;
      }
      return toRet;
    }

  },

  SOLUTION_2 {

    @Override
    public int solve(int n) {
      int toRet = 0;
      for (int i = 0; i < 32; i++) {
        toRet |= (n & 1) << 31 - i;
        n >>>= 1;
      }
      return toRet;
    }

  },

  SOLUTION_3 {

    @Override
    public int solve(int n) {
      n = n >>> 16 | n << 16;
      n = (n & 0xff00ff00) >>> 8 | (n & 0x00ff00ff) << 8;
      n = (n & 0xf0f0f0f0) >>> 4 | (n & 0x0f0f0f0f) << 4;
      n = (n & 0xcccccccc) >>> 2 | (n & 0x33333333) << 2;
      n = (n & 0xaaaaaaaa) >>> 1 | (n & 0x55555555) << 1;
      return n;
    }

  };

  public abstract int solve(int n);

  public static class TestReverseBits {

    private int n = 43261596;
    private int expected = 964176192;

    @Test
    public void testSolution0() {
      Assert.assertEquals(expected, SOLUTION_0.solve(n));
    }

    @Test
    public void testSolution1() {
      Assert.assertEquals(expected, SOLUTION_1.solve(n));
    }

    @Test
    public void testSolution2() {
      Assert.assertEquals(expected, SOLUTION_2.solve(n));
    }

    @Test
    public void testSolution3() {
      Assert.assertEquals(expected, SOLUTION_3.solve(n));
    }

  }

}

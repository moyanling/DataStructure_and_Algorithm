package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Write a program to find the n-th ugly number.
 * 
 * 
 * 
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10
 * ugly numbers.
 * 
 * 
 * 
 * Note that 1 is typically treated as an ugly number, and n does not exceed 1690.
 * 
 * 
 * 
 *   The naive approach is to call isUgly for every number until you reach the
 * nth one. Most numbers are not ugly. Try to focus your effort on generating
 * only the ugly ones.
 *   An ugly number must be multiplied by either 2, 3, or 5 from a smaller ugly
 * number.
 *   The key is how to maintain the order of the ugly numbers. Try a similar approach
 * of merging from three sorted lists: L1, L2, and L3.
 *   Assume you have Uk, the kth ugly number. Then Uk+1 must be Min(L1 * 2, L2
 * * 3, L3 * 5).
 * 
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/ugly-number-ii/">Ugly Number II</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum UglyNumberII {

  BOTTOM_UP_METHOD {

    @Override
    public int solve(int n) {
      return SuperUglyNumber.BOTTOM_UP_METHOD.solve(n, new int[] {2, 3, 5});
    }

  };

  public abstract int solve(int n);

  public static class TestUglyNumberII {

    int n = 10;
    int expected = 12;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, BOTTOM_UP_METHOD.solve(n));
    }

  }

}

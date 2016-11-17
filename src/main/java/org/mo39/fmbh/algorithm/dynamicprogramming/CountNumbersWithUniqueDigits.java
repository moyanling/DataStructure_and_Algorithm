package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ¡Ü x < 10^n.
 * 
 * @see <a href="https://leetcode.com/problems/count-numbers-with-unique-digits/">Count Numbers with
 *      Unique Digits</a>
 *
 * @author Jihan Chen
 *
 */
@ProblemSource(LEETCODE)
public enum CountNumbersWithUniqueDigits {

  CHEATER {

    private int[] answer = {1, 10, 91, 739, 5275, 32491, 168571, 712891, 2345851, 5611771, 8877691};

    @Override
    public int solve(int n) {
      return answer[Math.min(n, 10)];
    }

  },

  BOTTOM_UP_METHOD {

    @Override
    public int solve(int n) {
      n = Math.min(n, 10);
      int count = 1, pre = 9;
      for (int i = 1; i <= n; i++) {
        count += pre;
        pre *= 10 - i;
      }
      return count;
    }

  };

  public abstract int solve(int n);

  public static class TestCountNumbersWithUniqueDigits {

    private int expected = 8877691;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, CHEATER.solve(10));
      Assert.assertEquals(expected, BOTTOM_UP_METHOD.solve(10));
    }

  }

}

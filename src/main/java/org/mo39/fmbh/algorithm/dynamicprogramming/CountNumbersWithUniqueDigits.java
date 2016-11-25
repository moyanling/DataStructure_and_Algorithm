package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * Given a non-negative integer n, count all numbers with unique digits, x, where 0 &le; x &lt; 10n.
 * <br/>
 * Example:<br/>
 * Given n = 2, return 91. (The answer should be the total numbers in the range of 0 &le; x &lt;
 * 100, excluding [11,22,33,44,55,66,77,88,99])<br/>
 * A direct way is to use the backtracking approach.<br/>
 * Backtracking should contains three states which are (the current number, number of steps to get
 * that number and a bitmask which represent which number is marked as visited so far in the current
 * number). Start with state (0,0,0) and count all valid number till we reach number of steps equals
 * to 10n.<br/>
 * This problem can also be solved using a dynamic programming approach and some knowledge of
 * combinatorics.<br/>
 * Let f(k) = count of numbers with unique digits with length equals k.<br/>
 * f(1) = 10, ..., f(k) = 9 * 9 * 8 * ... (9 - k + 2) [The first factor is 9 because a number cannot
 * start with 0].<br/>
 * 
 * @see <a href="https://leetcode.com/problems/count-numbers-with-unique-digits/">Count Numbers With
 *      Unique Digits</a>
 * @author Jihan Chen
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

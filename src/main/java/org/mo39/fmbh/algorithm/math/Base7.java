package org.mo39.fmbh.algorithm.math;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given an integer, return its base 7 string representation.
 * 
 * Example 1:
 * 
 * Input: 100
 * Output: "202"
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: -7
 * Output: "-10"
 * 
 * 
 * 
 * Note:
 * The input will be in range of [-1e7, 1e7].
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/base-7/">Base 7</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum Base7 {

  SOLUTION {

    @Override
    public String solve(int num) {
      if (num == 0) return "0";
      boolean isNegative = num < 0;
      StringBuilder sb = new StringBuilder();
      for (; num != 0; num = num / 7) {
        sb.append(Math.abs(num % 7));
      }
      if (isNegative) sb.append('-');
      return sb.reverse().toString();
    }

  },

  CHEATER {

    @Override
    public String solve(int num) {
      return Integer.toString(num, 7);
    }

  };

  public abstract String solve(int num);

  public static class TestBase7 {

    int num = -7;
    String expected = "-10";

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(num));
      Assert.assertEquals(expected, CHEATER.solve(num));
    }

  }

}

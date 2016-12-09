package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * 
 * The robot can only move either down or right at any point in time. The robot is trying to reach
 * the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * 
 * How many possible unique paths are there?
 * 
 * 
 * 
 * Above is a 3 x 7 grid. How many possible unique paths are there?
 * 
 * 
 * Note: m and n will be at most 100.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/unique-paths/">Unique Paths</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum UniquePaths {

  MATH_SOLUTION {

    @Override
    public int solve(int m, int n) {
      return factorial(m + n - 2) / factorial(m - 1) / factorial(n - 1);
    }

    private int factorial(int n) {
      if (n == 0) return 1;
      return n * factorial(n - 1);
    }

  },

  BOTTOM_UP_METHOD {

    @Override
    public int solve(int m, int n) {
      int[][] memo = new int[m][n];
      Arrays.fill(memo[0], 1);
      for (int i = 0; i < m; i++) {
        memo[i][0] = 1;
      }
      for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
          memo[i][j] = memo[i - 1][j] + memo[i][j - 1];
        }
      }
      return memo[m - 1][n - 1];
    }

  };

  public abstract int solve(int m, int n);

  public static class TestUniquePath {

    private int m = 3;
    private int n = 7;
    private int expected = 28;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, MATH_SOLUTION.solve(m, n));
      Assert.assertEquals(expected, BOTTOM_UP_METHOD.solve(m, n));
    }

  }

}
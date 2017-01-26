package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a m x n grid filled with non-negative numbers, find a path from top left
 * to bottom right which minimizes the sum of all numbers along its path.
 * 
 * Note: You can only move either down or right at any point in time.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-path-sum/">Minimum Path Sum</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum MinimumPathSum {

  RECURSIVE_SOLUTION {

    @Override
    public int solve(int[][] grid) {
      return recur(grid, grid.length - 1, grid[0].length - 1);
    }

    private int recur(int[][] grid, int i, int j) {
      if (i == 0 && j == 0) return grid[0][0];
      int fromTop = Integer.MAX_VALUE, fromLeft = Integer.MAX_VALUE;
      if (i != 0) fromTop = recur(grid, i - 1, j);
      if (j != 0) fromLeft = recur(grid, i, j - 1);
      return Math.min(fromTop, fromLeft) + grid[i][j];
    }

  },

  BOTTOM_UP_METHOD_0 {

    @Override
    public int solve(int[][] grid) {
      int[][] memo = new int[grid.length][grid[0].length];
      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
          int cur = grid[i][j], fromTop = Integer.MAX_VALUE, fromLeft = Integer.MAX_VALUE;
          if (i == 0 && j == 0) {
            memo[i][j] = grid[0][0];
          } else {
            if (i > 0) fromTop = memo[i - 1][j];
            if (j > 0) fromLeft = memo[i][j - 1];
            memo[i][j] = Math.min(fromTop, fromLeft) + cur;
          }
        }
      }
      return memo[grid.length - 1][grid[0].length - 1];
    }

  },

  BOTTOM_UP_METHOD_1 {

    @Override
    public int solve(int[][] grid) {
      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
          int cur = grid[i][j], fromTop = Integer.MAX_VALUE, fromLeft = Integer.MAX_VALUE;
          if (i == 0 && j == 0) {
            grid[i][j] = grid[0][0];
          } else {
            if (i > 0) fromTop = grid[i - 1][j];
            if (j > 0) fromLeft = grid[i][j - 1];
            grid[i][j] = Math.min(fromTop, fromLeft) + cur;
          }
        }
      }
      return grid[grid.length - 1][grid[0].length - 1];
    }

  };

  public abstract int solve(int[][] grid);

  public static class TestMinimumPathSum {

    private int[][] grid = {{1, 2}, {1, 1}};
    private int expected = 3;

    @Test
    public void testSolutions() {
      for (MinimumPathSum sol : MinimumPathSum.values()) {
        Assert.assertEquals(expected, sol.solve(grid));
      }
    }

  }

}

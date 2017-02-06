package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Follow up for "Unique Paths":
 * 
 * Now consider if some obstacles are added to the grids. How many unique paths
 * would there be?
 * 
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * 
 * For example,
 * There is one obstacle in the middle of a 3x3 grid as illustrated below.
 * 
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * 
 * The total number of unique paths is 2.
 * 
 * Note: m and n will be at most 100.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/unique-paths-ii/">Unique Paths II</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum UniquePathsII {

  SOLUTION_0 {

    @Override
    public int solve(int[][] obstacleGrid) {
      int m = obstacleGrid.length, n = obstacleGrid[0].length;
      int[][] memo = new int[m][n];
      if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) return 0;
      memo[0][0] = 1;
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          if (i > 0 && obstacleGrid[i - 1][j] != 1) memo[i][j] += memo[i - 1][j];
          if (j > 0 && obstacleGrid[i][j - 1] != 1) memo[i][j] += memo[i][j - 1];
        }
      }
      return memo[m - 1][n - 1];
    }

  },

  SOLUTION_1 {

    @Override
    public int solve(int[][] obstacleGrid) {
      int width = obstacleGrid[0].length;
      int[] dp = new int[width];
      dp[0] = 1;
      for (int[] row : obstacleGrid) {
        for (int j = 0; j < width; j++) {
          if (row[j] == 1) dp[j] = 0;
          else if (j > 0) dp[j] += dp[j - 1];
        }
      }
      return dp[width - 1];
    }

  };

  public abstract int solve(int[][] obstacleGrid);

  public static class TestUniquePathsII {

    int[][] obstacleGrid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
    int expected = 2;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION_0.solve(obstacleGrid));
      Assert.assertEquals(expected, SOLUTION_1.solve(obstacleGrid));
    }

  }

}

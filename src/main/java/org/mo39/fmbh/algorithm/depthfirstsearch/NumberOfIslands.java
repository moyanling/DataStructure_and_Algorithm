package org.mo39.fmbh.algorithm.depthfirstsearch;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands
 * horizontally or vertically. You may assume all four edges of the grid are all
 * surrounded by water.
 * 
 * Example 1:
 * 11110110101100000000
 * Answer: 1
 * Example 2:
 * 11000110000010000011
 * Answer: 3
 * 
 * </pre>
 * @see <a href="https://leetcode.com/problems/number-of-islands/">Number Of Islands</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum NumberOfIslands {

  SOLUTION {

    private int n;
    private int m;

    @Override
    public int solve(char[][] grid) {
      int count = 0;
      n = grid.length;
      if (n == 0) return 0;
      m = grid[0].length;
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++)
          if (grid[i][j] == '1') {
            recur(grid, i, j);
            ++count;
          }
      }
      return count;
    }

    void recur(char[][] grid, int i, int j) {
      if (i < 0 || j < 0 || i >= n || j >= m || grid[i][j] != '1') return;
      grid[i][j] = '0';
      recur(grid, i + 1, j);
      recur(grid, i - 1, j);
      recur(grid, i, j + 1);
      recur(grid, i, j - 1);
    }

  };

  public abstract int solve(char[][] grid);

  public static class TestNumberOfIslands {

    int expected = 1;

    @Test
    public void testSolutions() {
      for (NumberOfIslands sol : NumberOfIslands.values()) {
        char[][] grid = {{'1', '1', '1'}, {'0', '1', '0'}, {'1', '1', '1'}};
        Assert.assertEquals(expected, sol.solve(grid));
      }
    }

  }

}
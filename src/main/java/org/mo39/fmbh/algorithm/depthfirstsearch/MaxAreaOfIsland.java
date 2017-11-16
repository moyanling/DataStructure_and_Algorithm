package org.mo39.fmbh.algorithm.depthfirstsearch;

import org.mo39.fmbh.common.annotation.ProblemSource;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

/**
 *
 *
 * <pre>
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's
 * (representing land) connected 4-directionally (horizontal or vertical.)  You
 * may assume all four edges of the grid are surrounded by water.
 *
 * Find the maximum area of an island in the given 2D array.
 * (If there is no island, the maximum area is 0.)
 *
 * Example 1:
 *
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 *
 * Given the above grid, return 6.
 *
 * Note the answer is not 11, because the island must be connected 4-directionally.
 *
 *
 * Example 2:
 * [[0,0,0,0,0,0,0,0]]
 * Given the above grid, return 0.
 *
 *
 * Note:
 * The length of each dimension in the given grid does not exceed 50.
 * </pre>
 *
 * @see <a href="https://leetcode.com/problems/max-area-of-island/">Max Area Of Island</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum MaxAreaOfIsland {
  SOLUTION {
    @Override
    public int maxAreaOfIsland(int[][] grid) {
      int max = 0;
      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
          max = Math.max(max, dfs(grid, i, j));
        }
      }
      return max;
    }

    int dfs(int[][] grid, int i, int j) {
      if (grid[i][j] == 0) return 0;
      int area = 1;
      grid[i][j] = 0;
      if (i > 0 && grid[i - 1][j] == 1) area += dfs(grid, i - 1, j);
      if (j > 0 && grid[i][j - 1] == 1) area += dfs(grid, i , j - 1);
      if (i < grid.length - 1 && grid[i + 1][j] == 1) area += dfs(grid, i + 1, j);
      if (j < grid[0].length - 1 && grid[i][j + 1] == 1) area += dfs(grid, i, j + 1);
      return area;
    }
  };

  public abstract int maxAreaOfIsland(int[][] grid);
}

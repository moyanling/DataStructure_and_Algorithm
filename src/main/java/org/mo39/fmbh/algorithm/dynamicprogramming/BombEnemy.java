package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero),
 * return the maximum enemies you can kill using one bomb. The bomb kills all the enemies in the
 * same row and column from the planted point until it hits the wall since the wall is too strong to
 * be destroyed. Note that you can only put the bomb at an empty cell.
 * 
 * Example:
 * 
 * For the given grid
 * 
 * 0 E 0 0
 * E 0 W E
 * 0 E 0 0
 * 
 * return 3. (Placing a bomb at (1,1) kills 3 enemies)
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/bomb-enemy/">Bomb Enemy</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum BombEnemy {

  /**
   * Time complexity is <b>O(mn(m+n))</b>
   */
  SOLUTION_0 {

    @Override
    public int solve(char[][] grid) {
      if (grid.length == 0 || grid[0].length == 0) return 0;
      int[][] memo = new int[grid.length][grid[0].length];
      int max = 0;
      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
          if (grid[i][j] == 'E') max = Math.max(max, updateMemo(memo, grid, i, j));
        }
      }
      return max;
    }

    private int updateMemo(int[][] memo, char[][] grid, int i, int j) {
      int max = 0;
      for (int k = i + 1; k < grid.length && grid[k][j] != 'W'; k++) {
        if (grid[k][j] != 'E') max = Math.max(max, ++memo[k][j]);
      }
      for (int k = i - 1; k > -1 && grid[k][j] != 'W'; k--) {
        if (grid[k][j] != 'E') max = Math.max(max, ++memo[k][j]);
      }
      for (int k = j + 1; k < grid[0].length && grid[i][k] != 'W'; k++) {
        if (grid[i][k] != 'E') max = Math.max(max, ++memo[i][k]);
      }
      for (int k = j - 1; k > -1 && grid[i][k] != 'W'; k--) {
        if (grid[i][k] != 'E') max = Math.max(max, ++memo[i][k]);
      }
      return max;
    }

  },

  SOLUTION_1 {

    @Override
    public int solve(char[][] grid) {
      if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
      int max = 0, row = 0;
      int[] col = new int[grid[0].length];
      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
          if (j == 0 || grid[i][j - 1] == 'W') row = killRowEnemies(grid, i, j);
          if (i == 0 || grid[i - 1][j] == 'W') col[j] = killColEnemies(grid, i, j);
          if (grid[i][j] == '0') max = row + col[j] > max ? row + col[j] : max;
        }
      }
      return max;
    }

    // calculate killed enemies for row i from column j
    private int killRowEnemies(char[][] grid, int i, int j) {
      int num = 0;
      for (; j < grid[0].length && grid[i][j] != 'W'; j++) {
        if (grid[i][j] == 'E') num++;
      }
      return num;
    }

    // calculate killed enemies for column j from row i
    private int killColEnemies(char[][] grid, int i, int j) {
      int num = 0;
      for (; i < grid.length && grid[i][j] != 'W'; i++) {
        if (grid[i][j] == 'E') num++;
      }
      return num;
    }

  };

  public abstract int solve(char[][] grid);

  public static class TestBombEnemy {

    private char[][] grid = {{'0', 'E', '0', '0'}, {'E', '0', 'W', 'E'}, {'0', 'E', '0', '0'}};
    private int expected = 3;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION_0.solve(grid));
    }

  }

}

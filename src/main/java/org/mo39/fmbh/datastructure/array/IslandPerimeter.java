package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0
 * represents water. Grid cells are connected horizontally/vertically (not diagonally). The grid is
 * completely surrounded by water, and there is exactly one island (i.e., one or more connected land
 * cells). The island doesn't have "lakes" (water inside that isn't connected to the water around
 * the island). One cell is a square with side length 1. The grid is rectangular, width and height
 * don't exceed 100. Determine the perimeter of the island.<br/>
 * Example:<br/>
 * [[0,1,0,0],<br/>
 * [1,1,1,0],<br/>
 * [0,1,0,0],<br/>
 * [1,1,0,0]]<br/>
 * Answer: 16<br/>
 * Explanation: The perimeter is the 16 yellow stripes in the image below:
 *
 * @see <a href="https://leetcode.com/problems/island-perimeter/">Island Perimeter</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum IslandPerimeter {

  SOLUTION {

    @Override
    public int solve(int[][] grid) {
      int perimeter = 0;
      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
          if (grid[i][j] == 1) {
            perimeter += 4;
            if (i > 0 && grid[i - 1][j] == 1) perimeter -= 2;
            if (j > 0 && grid[i][j - 1] == 1) perimeter -= 2;
          }
        }
      }
      return perimeter;
    }

  };

  public abstract int solve(int[][] grid);

  public static class TestIslanPerimeter {

    private int[][] grid = {{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}};
    private int expected = 16;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(grid));
    }

  }

}

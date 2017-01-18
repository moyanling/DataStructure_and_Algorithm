package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given an integer n, generate a square matrix filled with elements from 1 to
 * n2 in spiral order.
 * 
 * 
 * For example,
 * Given n = 3,
 * 
 * You should return the following matrix:
 * 
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/spiral-matrix-ii/">Spiral Matrix II</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum SpiralMatrixII {

  SOLUTION {

    @Override
    public int[][] solve(int n) {
      int[][] result = new int[n][n];
      int i = 0, j = -1, v = 1;
      while (v <= n * n) {
        while (++j < n && result[i][j] == 0) {
          result[i][j] = v++;
        }
        j--;
        while (++i < n && result[i][j] == 0) {
          result[i][j] = v++;
        }
        i--;
        while (--j > -1 && result[i][j] == 0) {
          result[i][j] = v++;
        }
        j++;
        while (--i > -1 && result[i][j] == 0) {
          result[i][j] = v++;
        }
        i++;
      }
      return result;
    }

  };

  public abstract int[][] solve(int n);

  public static class TestSpiralMatrixII {

    private int n = 5;
    private int[][] expecteds = {{1, 2, 3, 4, 5}, {16, 17, 18, 19, 6}, {15, 24, 25, 20, 7},
        {14, 23, 22, 21, 8}, {13, 12, 11, 10, 9},};

    @Test
    public void testSolutions() {
      Assert.assertArrayEquals(expecteds, SOLUTION.solve(n));
    }

  }

}

package org.mo39.fmbh.algorithm.divideandconquer;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the
 * following properties:
 * 
 * 
 * 
 * Integers in each row are sorted in ascending from left to right. Integers in each column are
 * sorted in ascending from top to bottom.
 * 
 * 
 * For example,
 * 
 * Consider the following matrix:
 * 
 * 
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 
 * 
 * 
 * Given target = 5, return true. Given target = 20, return false.
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/search-a-2d-matrix-ii/">Search A 2 D Matrix II</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum SearchA2DMatrixII {

  SADDLE_BACK {

    @Override
    public boolean solve(int[][] matrix, int target) {
      if (matrix == null || matrix.length < 1 || matrix[0].length < 1) { return false; }
      int col = matrix[0].length - 1;
      int row = 0;
      while (col >= 0 && row <= matrix.length - 1) {
        if (target == matrix[row][col]) return true;
        else if (target < matrix[row][col]) col--;
        else if (target > matrix[row][col]) row++;
      }
      return false;
    }

  },

  DIVID_AND_CONQURE {

    @Override
    public boolean solve(int[][] matrix, int target) {
      if (matrix.length == 0 || matrix[0].length == 0) return false;
      return recur(matrix, target, new int[] {0, 0, matrix.length - 1, matrix[0].length - 1});
    }

    private boolean recur(int[][] matrix, int target, int[] field) {
      if (field[0] == field[2] || field[1] == field[3]) {
        if (field[0] == field[2]) {
          int search = Arrays.binarySearch(matrix[field[0]], field[1], field[3] + 1, target);
          if (search < 0) return false;
          return true;
        }
        for (int i = field[0]; i <= field[2]; i++) {
          if (matrix[i][field[1]] == target) return true;
        }
        return false;
      }
      int[] mid = {field[0] + field[2] >>> 1, field[1] + field[3] >>> 1};
      boolean topLeft = false, bottomLeft = false, topRight = false, bottomRight = false;
      // Top left
      if (matrix[field[0]][field[1]] <= target && matrix[mid[0]][mid[1]] >= target)
        topLeft = recur(matrix, target, new int[] {field[0], field[1], mid[0], mid[1]});
      // Bottom left
      if (matrix[mid[0] + 1][field[1]] <= target && matrix[field[2]][mid[1]] >= target)
        bottomLeft = recur(matrix, target, new int[] {mid[0] + 1, field[1], field[2], mid[1]});
      // Top right
      if (matrix[field[0]][mid[1] + 1] <= target && matrix[mid[0]][field[3]] >= target)
        topRight = recur(matrix, target, new int[] {field[0], mid[1] + 1, mid[0], field[3]});
      // Bottom right
      if (matrix[mid[0] + 1][mid[1] + 1] <= target && matrix[field[2]][field[3]] >= target)
        bottomRight = recur(matrix, target, new int[] {mid[0] + 1, mid[1] + 1, field[2], field[3]});
      return topLeft | bottomLeft | topRight | bottomRight;
    }

  };

  public abstract boolean solve(int[][] matrix, int target);

  public static class TestSearchA2DMatrixII {

    private int[][] matrix = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15},
        {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}};
    private int target = 5;
    private boolean expected = true;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, DIVID_AND_CONQURE.solve(matrix, target));
    }

  }

}

package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 * Follow up:
 * Could you do this in-place?
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/rotate-image/">Rotate Image</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum RotateImage {

  SOLUTION_0 {

    @Override
    public void solve(int[][] matrix) {
      int start = 0, end = matrix.length - 1;
      while (start < end) {
        for (int i = 0; i < end - start; i++) {
          swap(matrix, start, i + start, i + start, end);
          swap(matrix, start, i + start, end, end - i);
          swap(matrix, start, i + start, end - i, start);
        }
        start++;
        end--;
      }
    }

  },

  /**
   * <pre>
   * clockwise rotate
   * first reverse up to down, then swap the symmetry 
   * 1 2 3     7 8 9     7 4 1
   * 4 5 6  => 4 5 6  => 8 5 2
   * 7 8 9     1 2 3     9 6 3
   * 
   * anticlockwise rotate
   * first reverse left to right, then swap the symmetry
   * 1 2 3     3 2 1     3 6 9
   * 4 5 6  => 6 5 4  => 2 5 8
   * 7 8 9     9 8 7     1 4 7
   * </pre>
   */
  SOLUTION_1 {

    @Override
    public void solve(int[][] matrix) {
      ArrayUtils.reverse(matrix);
      for (int i = 0; i < matrix.length; ++i) {
        for (int j = i + 1; j < matrix[i].length; ++j) {
          swap(matrix, i, j, j, i);
        }
      }
    }

  };

  protected void swap(int[][] matrix, int i1, int j1, int i2, int j2) {
    if (i1 == i2 && j1 == j2) return;
    matrix[i1][j1] += matrix[i2][j2];
    matrix[i2][j2] = matrix[i1][j1] - matrix[i2][j2];
    matrix[i1][j1] = matrix[i1][j1] - matrix[i2][j2];
  }

  public abstract void solve(int[][] matrix);

  public static class TestRotateImage {

    private int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
    private int[][] expecteds = {{13, 9, 5, 1}, {14, 10, 6, 2}, {15, 11, 7, 3}, {16, 12, 8, 4}};

    @Test
    public void testSolution0() {
      SOLUTION_0.solve(matrix);
      Assert.assertArrayEquals(expecteds, matrix);
    }

    @Test
    public void testSolution1() {
      SOLUTION_1.solve(matrix);
      Assert.assertArrayEquals(expecteds, matrix);
    }

  }

}

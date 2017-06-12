package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * In MATLAB, there is a very useful function called 'reshape', which can reshape
 * a matrix into a new one with different size but keep its original data.
 * 
 * 
 * 
 * You're given a matrix represented by a two-dimensional array, and two positive
 * integers r and c representing the row number and column number of the wanted
 * reshaped matrix, respectively.
 * 
 *  The reshaped matrix need to be filled with all the elements of the original
 * matrix in the same row-traversing order as they were.
 * 
 * 
 * 
 * If the 'reshape' operation with given parameters is possible and legal, output
 * the new reshaped matrix; Otherwise, output the original matrix.
 * 
 * 
 * Example 1:
 * 
 * Input: 
 * nums = 
 * [[1,2],
 *  [3,4]]
 * r = 1, c = 4
 * Output: 
 * [[1,2,3,4]]
 * Explanation:The row-traversing of nums is [1,2,3,4]. The new reshaped matrix
 * is a 1 * 4 matrix, fill it row by row by using the previous list.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: 
 * nums = 
 * [[1,2],
 *  [3,4]]
 * r = 2, c = 4
 * Output: 
 * [[1,2],
 *  [3,4]]
 * Explanation:There is no way to reshape a 2 * 2 matrix to a 2 * 4 matrix. So
 * output the original matrix.
 * 
 * 
 * 
 * Note:
 * 
 * The height and width of the given matrix is in range [1, 100].
 * The given r and c are all positive.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/reshape-the-matrix/">Reshape The Matrix</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum ReshapeTheMatrix {

  SOLUTION {

    @Override
    public int[][] solve(int[][] nums, int r, int c) {
      if (nums == null || nums.length < 1) return nums;
      int m = nums.length, n = nums[0].length;
      if (m * n != r * c) return nums;
      int size = r * c, result[][] = new int[r][c];
      for (int i = 0; i < size; i++) {
        result[i / c][i % c] = nums[i / n][i % n];
      }
      return result;
    }

  };

  public abstract int[][] solve(int[][] nums, int r, int c);

  public static class TestReshapeTheMatrix {

    int[][] nums = {{1, 2}, {3, 4}};
    int[][] expecteds = {{1, 2, 3, 4}};
    int r = 1, c = 4;

    @Test
    public void testSolutions() {
      Assert.assertArrayEquals(expecteds, SOLUTION.solve(nums, r, c));
    }

  }

}

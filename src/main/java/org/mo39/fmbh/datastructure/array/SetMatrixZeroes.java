package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given a m x n matrix, if an element is 0, set its entire row and column to
 * 0. Do it in place.
 * 
 * 
 * click to show follow up.
 * 
 * Follow up:
 * 
 * 
 * Did you use extra space?
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/set-matrix-zeroes/">Set Matrix Zeroes</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum SetMatrixZeroes {

  /**
   * 1. Store the zero in a new matrix. O(m*n) extra space</br>
   * 2. Store the cols and rows to be set to zero. O(m + n) extra space.</br>
   * 3. Store the cols and rows to be set to zero at the first row/col of that row/col.</br>
   * 4. If do this in python or is solving a Integer matrix, set null instead of 0.
   */
  SOLUTION {

    @Override
    public void solve(int[][] matrix) {
      int col0 = 1, rows = matrix.length, cols = matrix[0].length;
      for (int i = 0; i < rows; i++) {
        if (matrix[i][0] == 0) col0 = 0;
        for (int j = 1; j < cols; j++)
          if (matrix[i][j] == 0) matrix[i][0] = matrix[0][j] = 0;
      }
      for (int i = rows - 1; i >= 0; i--) {
        for (int j = cols - 1; j >= 1; j--)
          if (matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;
        if (col0 == 0) matrix[i][0] = 0;
      }
    }

  };

  public abstract void solve(int[][] matrix);

}

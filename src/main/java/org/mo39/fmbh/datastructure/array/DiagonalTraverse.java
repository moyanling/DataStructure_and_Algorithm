package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given a matrix of M x N elements (M rows, N columns), return all elements of
 * the matrix in diagonal order as shown in the below image. 
 * 
 * 
 * Example:
 * 
 * Input:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * Output:  [1,2,4,7,5,3,6,8,9]
 * Explanation:
 * 
 * 
 * 
 * 
 * Note:
 * 
 * The total number of elements of the given matrix will not exceed 10,000.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/diagonal-traverse/">Diagonal Traverse</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum DiagonalTraverse {

  /**
   * This one is to control the walk direction. </br>
   * //TODO Try another one: calculate the index for each element in the matrix
   */
  SOLUTION {

    int m, n;

    @Override
    public int[] solve(int[][] matrix) {
      if (matrix.length == 0) return new int[0];
      m = matrix.length;
      n = matrix[0].length;
      int[] result = new int[m * n];
      boolean flag = true;
      for (int k = 0, i = 1, j = -1; k < result.length; k++) {
        if (flag) {
          if (check(i - 1, j + 1)) {
            i--;
            j++;
          } else {
            flag = false;
            if (j + 1 < n) j++;
            else i++;
          }
        } else {
          if (check(i + 1, j - 1)) {
            i++;
            j--;
          } else {
            flag = true;
            if (i + 1 < m) i++;
            else j++;
          }
        }
        result[k] = matrix[i][j];
      }
      return result;
    }

    boolean check(int i, int j) {
      if (0 <= i && i < m && 0 <= j && j < n) return true;
      return false;
    }

  };

  public abstract int[] solve(int[][] matrix);

}

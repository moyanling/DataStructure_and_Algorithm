package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

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
 * Integers in each row are sorted from left to right. The first integer of each row is greater than
 * the last integer of the previous row.
 * 
 * 
 * 
 * 
 * For example,
 * 
 * Consider the following matrix:
 * 
 * 
 * [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * 
 * 
 * Given target = 3, return true.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/search-a-2d-matrix/">Search A 2 D Matrix</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum SearchA2DMatrix {

  SOLUTION {

    @Override
    public boolean solve(int[][] matrix, int target) {
      int upper = matrix.length * matrix[0].length - 1, lower = 0;
      while (lower <= upper) {
        int mid = lower + upper >>> 1;
        int value = matrix[mid / matrix[0].length][mid % matrix[0].length];
        if (target == value) return true;
        else if (target < value) upper = mid - 1;
        else lower = mid + 1;
      }
      return false;
    }

  };

  public abstract boolean solve(int[][] matrix, int target);

  public static class TestSearchA2DMatrix {

    int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
    int target = 8;
    boolean expected = false;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(new int[][] {{1, 1}}, 2));
    }

  }

}

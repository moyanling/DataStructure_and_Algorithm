package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.INTRODUCTION_TO_ALGORITHM;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * Given a chain of matrix, decide the order of the chain so that calculation can be the least when
 * doing the chain multiplication.
 * 
 * @author Jihan Chen
 *
 */
@ProblemSource(INTRODUCTION_TO_ALGORITHM)
public enum MatrixChainOrdering {

  /**
   * This is the recursive solution yielded by myself. A nice practice but the sub-case I found is
   * not good. My minimum sub-case is an array of 3 length. And main process is handling the
   * construction of sub array. In addition, since this solution is using a minimum of 3 length
   * sub-array instead of the concept of array segmentation, it has to handle the sequence of
   * executing manually. That is to execute from left to right, then execute from right to left.
   * 
   */
  RECURSIVE_SOLUTION_0() {

    @Override
    public int solve(int[] m) {
      if (m.length <= 3) return m[0] * m[1] * m[2];
      Integer result = null;
      int[] mLeft = new int[3];
      System.arraycopy(m, 0, mLeft, 0, 3);
      int leftResult = solve(mLeft);
      int[] mRight = new int[m.length - 1];
      System.arraycopy(m, 3, mRight, 2, m.length - 3);
      mRight[0] = mLeft[0];
      mRight[1] = mLeft[mLeft.length - 1];
      int rightResult = solve(mRight);
      if (result == null || result > leftResult + rightResult) result = leftResult + rightResult;
      mRight = new int[3];
      System.arraycopy(m, m.length - 3, mRight, 0, 3);
      rightResult = solve(mRight);
      mLeft = new int[m.length - 1];
      System.arraycopy(m, 0, mLeft, 0, m.length - 3);
      mLeft[mLeft.length - 2] = mRight[0];
      mLeft[mLeft.length - 1] = mRight[mRight.length - 1];
      leftResult = solve(mLeft);
      if (result == null || result > leftResult + rightResult) result = leftResult + rightResult;
      return result;
    }
  },

  /**
   * This is the solution provided by the book. It use a concept of array segmentation to "split"
   * the array, solve them separately, put the results together and add the result of multiply these
   * two arrays. A nice and clean function with a deeper understanding of the problem. <br>
   * //TODO go back and understand the solution.
   * 
   */
  RECURSIVE_SOLUTION_1() {

    @Override
    public int solve(int[] m) {
      return solve(m, 1, m.length - 1);
    }

    public int solve(int[] m, int lower, int upper) {
      if (lower == upper) return 0;
      Integer min = null;
      for (int i = lower; i < upper; i++) {
        int result = solve(m, lower, i) + solve(m, i + 1, upper) + m[lower - 1] * m[i] * m[upper];
        if (min == null || min > result) min = result;
      }
      return min;
    }

  },

  /**
   * Consider the upper recursive solution, we can discover that the solution of a longer array is
   * based on the solution of a short array, so the memo should contain the solution of shorter
   * array.<br>
   * //TODO go back and understand the solution.
   * 
   */
  BOTTOM_UP_METHOD() {

    private int[][] memo;

    @Override
    public int solve(int[] m) {
      memo = new int[m.length][m.length];
      return handle(m);
    }

    private int handle(int[] m) {
      for (int len = 2; len < m.length; len++) {
        for (int i = 1; i <= m.length - len; i++) {
          int j = i + len - 1;
          Integer localMin = null;
          for (int k = i; k < j; k++) {
            int result = memo[i][k] + memo[k + 1][j] + m[i - 1] * m[k] * m[j];
            if (localMin == null || localMin > result) localMin = result;
          }
          if (len == m.length - 1) return localMin;
          memo[i][j] = localMin;
        }
      }
      // This should be a dead code.
      return 0;
    }

  };

  public abstract int solve(int[] m);

  public static class TestMatrixChainOrdering {

    private int[] arr = {10, 100, 5, 50, 10};
    private int expected = 8000;

    @Test
    public void testRecursiveSolution0() {
      Assert.assertEquals(expected, MatrixChainOrdering.RECURSIVE_SOLUTION_0.solve(arr));
    }

    @Test
    public void testRecursiveSolution1() {
      Assert.assertEquals(expected, MatrixChainOrdering.RECURSIVE_SOLUTION_1.solve(arr));
    }

    @Test
    public void testDynamicProgramming() {
      Assert.assertEquals(expected, MatrixChainOrdering.BOTTOM_UP_METHOD.solve(arr));
    }

  }

}

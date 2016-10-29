package org.mo39.fmbh.algorithm.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mo39.fmbh.common.Z;

/**
 * Given a chain of matrix, decide the order of the chain so that calculation can be the least when
 * doing the chain multiplication.
 * 
 * @author Jihan Chen
 *
 */
public enum MatrixChainOrdering {

  RECUSIVE_SOLUTION() {

    @Override
    public int solve(int[] m) {
      return solve(m, 0, m.length - 1);
    }

    // @Override
    // public int solve(int[] m) {
    // if (m.length <= 3) return m[0] * m[1] * m[2];
    // Integer result = null;
    // int[] mLeft = new int[3];
    // System.arraycopy(m, 0, mLeft, 0, 3);
    // int leftResult = solve(mLeft);
    // Z.print(mLeft);
    // int[] mRight = new int[m.length - 1];
    // System.arraycopy(m, 3, mRight, 2, m.length - 3);
    // mRight[0] = mLeft[0];
    // mRight[1] = mLeft[mLeft.length - 1];
    // int rightResult = solve(mRight);
    // Z.print(mRight);
    // Z.print(leftResult + rightResult);
    // if (result == null || result > leftResult + rightResult) {
    // result = leftResult + rightResult;
    // }
    // list.add(leftResult + rightResult);
    // mRight = new int[3];
    // System.arraycopy(m, m.length - 3, mRight, 0, 3);
    // rightResult = solve(mRight);
    // Z.print(mRight);
    // mLeft = new int[m.length - 1];
    // System.arraycopy(m, 0, mLeft, 0, m.length - 3);
    // mLeft[mLeft.length - 2] = mRight[0];
    // mLeft[mLeft.length - 1] = mRight[mRight.length - 1];
    // leftResult = solve(mLeft);
    // Z.print(mLeft);
    // Z.print(rightResult + leftResult);
    // if (result == null || result > leftResult + rightResult) {
    // result = leftResult + rightResult;
    // }
    // list.add(leftResult + rightResult);
    // Z.print();
    // return result;
    // // return solve(m, 0, m.length - 1).num;
    // }

    public int solve(int[] m, int lower, int upper) {
      if (lower == upper) {
//        Z.print(lower);
        return 0;
      }
      Integer min = null;
      for (int i = lower; i <= upper; i++) {
        int result = solve(m, lower, i) + solve(m, i, upper) + m[lower] * m[i] * m[upper];
        if (min == null || min > result) min = result;
      }
      return min;
    }



  };
  public List<Integer> list = new ArrayList<>();

  private static class Result {

    public int num;
    public List<Integer> indexes;

    public Result(int num, List<Integer> indexes) {
      this.num = num;
      this.indexes = indexes;
    }

    @Override
    public String toString() {
      return num + " : " + indexes;
    }

  }

  public abstract int solve(int[] m);

  public static class TestMatrixChainOrdering {

    private int[] arr = {10, 100, 5, 50, 10};

    @Test
    public void testRecursiveSolution() {
      Z.print(MatrixChainOrdering.RECUSIVE_SOLUTION.solve(arr));
    }

  }

}

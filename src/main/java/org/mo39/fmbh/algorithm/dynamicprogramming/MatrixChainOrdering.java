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

    // @Override
    // public Result solve(int[] m) {
    // // return solve(m, 0, m.length - 1);
    // return null;
    // }

    @Override
    public int solve(int[] m) {
      // if (q - p <= 2) {
      // Z.print(p + "\t" + q);
      // return new Result(m[p] * m[p + 1] * m[p + 2], Lists.newArrayList(m[p], m[p + 1], m[p +
      // 2]));
      // }
      // Result min = null;
      // for (int i = p; i < q; i++) {
      // for (int r = i + 2; r < q; r++) {
      // Result result0 = solve(m, i, r);
      // // i + 1 is not needed here.
      // Result result1 = solve(m, r, q);
      // if (min == null || result0.num + result1.num < min.num) {
      // List<Integer> list = new ArrayList<>();
      // list.addAll(result0.indexes);
      // list.addAll(result1.indexes);
      // min = new Result(result0.num + result1.num, list);
      // }
      // }
      // }
      if (m.length <= 3) { return m[0] * m[1] * m[2]; }
      Integer result = null;
      int[] mLeft = new int[3];
      System.arraycopy(m, 0, mLeft, 0, 3);
      int leftResult = solve(mLeft);
      Z.print(mLeft);
      int[] mRight = new int[m.length - 1];
      System.arraycopy(m, 3, mRight, 2, m.length - 3);
      mRight[0] = mLeft[0];
      mRight[1] = mLeft[mLeft.length - 1];
      int rightResult = solve(mRight);
      Z.print(mRight);
      Z.print(leftResult + rightResult);
      if (result == null || result > leftResult + rightResult) {
        result = leftResult + rightResult;
      }
      list.add(leftResult + rightResult);
      mRight = new int[3];
      System.arraycopy(m, m.length - 3, mRight, 0, 3);
      rightResult = solve(mRight);
      Z.print(mRight);
      mLeft = new int[m.length - 1];
      System.arraycopy(m, 0, mLeft, 0, m.length - 3);
      mLeft[mLeft.length - 2] = mRight[0];
      mLeft[mLeft.length - 1] = mRight[mRight.length - 1];
      leftResult = solve(mLeft);
      Z.print(mLeft);
      Z.print(rightResult + leftResult);
      if (result == null || result > leftResult + rightResult) {
        result = leftResult + rightResult;
      }
      list.add(leftResult + rightResult);
      Z.print();
      return result;
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
      Z.print(MatrixChainOrdering.RECUSIVE_SOLUTION.list);
    }

  }

}

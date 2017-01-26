package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.INTRODUCTION_TO_ALGORITHM;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;


/**
 * Given an array, matching to the price of rod with length starts from 1 to array.length. Then find
 * the maximum price for a given rod. The rod can be cut into multiple rods.
 * 
 * @author Jihan Chen
 *
 */
@ProblemSource(INTRODUCTION_TO_ALGORITHM)
public enum CuttingRod {

  /**
   * This is a recursive solution. The time complexity is O(2^n).<br>
   * //TODO Go back and understand this after some time.
   * 
   */
  RECURSIVE_SOLUTION() {

    @Override
    public int solve(int rod) {
      if (rod == 0) return 0;
      int max = 0;
      for (int i = 1; i < rod + 1; i++) {
        max = Math.max(max, price[i - 1] + solve(rod - i));
      }
      return max;
    }

  },

  /**
   * This is an improvement to recursive solution. The recursive solution will need to solve the
   * same sub-case many times. Take rod = 10 as an example, when i is 1, it needs to solve the case
   * when it's cut into 1 and 9. And when i is 9, the rod will be cut into 9 and 1. Here's the
   * duplicate. In many sub case this happens as well. So an hash map or an array is used to stored
   * the result and speed up the calculation.
   * 
   */
  TOP_DOWN_WITH_MEMO() {

    private int[] memo = new int[price.length];

    @Override
    public int solve(int rod) {
      if (rod == 0) return 0;
      int max = 0;
      for (int i = 1; i < rod + 1; i++) {
        if (memo[rod - i] == 0) memo[rod - i] = solve(rod - i);
        max = Math.max(max, price[i - 1] + memo[rod - i]);
      }
      return max;
    }

  },

  /**
   * This one builds up the solution from the very first case,<br>
   * that is when i = 0, max = 0, to i = rod, max = memo[rod]. This is like a traverse, from bottom
   * to the top. Solve the sub problem from a very basic level and build up the answer to the
   * goal.//TODO need more understanding from abstract point of view. Go back here later.
   * <p>
   * <code>codeMemo</code> is used to save the rod cutting plan for each length smaller than rod.
   */
  BOTTOM_UP_METHOD() {

    @Override
    public int solve(int rod) {
      int[] memo = new int[rod + 1];
      int[][] cutMemo = new int[rod + 1][];
      cutMemo[0] = new int[] {0};
      for (int i = 1; i < rod + 1; i++) {
        int max = 0;
        int[] cut = null;
        for (int j = 1; j < i + 1; j++) {
          int newMax = price[j - 1] + memo[i - j];
          if (newMax > max) {
            max = newMax;
            cut = new int[cutMemo[i - j].length + 1];
            cut[0] = j;
            System.arraycopy(cutMemo[i - j], 0, cut, 1, cutMemo[i - j].length);
          }
        }
        memo[i] = max;
        cutMemo[i] = cut;
      }
      return memo[rod];
    }

  };

  public final int[] price = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

  public abstract int solve(int rod);

  public static class TestCuttingRod {

    private void verify(CuttingRod sol) {
      int[] expected = {1, 5, 8, 10, 13, 17, 18, 22, 25, 30};
      for (int i = 0; i < sol.price.length; i++) {
        Assert.assertEquals(expected[i], sol.solve(i + 1));
      }
    }

    @Test
    public void testSolution() {
      verify(CuttingRod.RECURSIVE_SOLUTION);
      verify(CuttingRod.TOP_DOWN_WITH_MEMO);
      verify(CuttingRod.BOTTOM_UP_METHOD);
    }

  }



}

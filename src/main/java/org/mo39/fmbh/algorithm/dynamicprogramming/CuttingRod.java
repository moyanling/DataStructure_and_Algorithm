package org.mo39.fmbh.algorithm.dynamicprogramming;

import org.junit.Assert;
import org.junit.Test;


/**
 * Given an array, matching to the price of rod with length starts from 1 to array.length. Then find
 * the maximum price for a given rod. The rod can be cut into multiple rods.
 * 
 * @author Jihan Chen
 *
 */
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
  TOP_DOWN_WITH_MEMOIZATION() {

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

  };

  public final int[] price = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

  public abstract int solve(int rod);

  public static class TestCuttingRod {

    private void verifySolution(CuttingRod sol) {
      int[] expected = {1, 5, 8, 10, 13, 17, 18, 22, 25, 30};
      for (int i = 0; i < sol.price.length; i++) {
        Assert.assertEquals(expected[i], sol.solve(i + 1));
      }
    }

    @Test
    public void testSolution() {
      verifySolution(CuttingRod.RECURSIVE_SOLUTION);
      verifySolution(CuttingRod.TOP_DOWN_WITH_MEMOIZATION);
    }

  }



}

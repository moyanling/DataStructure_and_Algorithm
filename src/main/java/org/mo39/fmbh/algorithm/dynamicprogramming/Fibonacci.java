package org.mo39.fmbh.algorithm.dynamicprogramming;

import org.junit.Assert;
import org.junit.Test;

public enum Fibonacci {

  /**
   * // Resolve the time complexity.
   *
   */
  RECUSIVE_SOLUTION() {

    @Override
    public int solve(int n) {
      if (n == 0) return 0;
      if (n == 1) return 1;
      return solve(n - 2) + solve(n - 1);
    }

  },

  /**
   * 1. Cannot make <code>answer</code> static because it's an inner class of Fibonacci and thus can
   * not have static variable and methods.<br>
   * 2. It's much faster than recursive solution. The time complexity is <b>O(n)</b>.<br>
   * 3. With <code>answer</code>, a request of n that is smaller then previously requested can be
   * returned in O(1).<br>
   * 4. <code>answer</code> is helpful to some extent, but not that helpful because it exceed
   * {@link Integer#MAX_VALUE} very fast (at n = 45, result = 433494437), so <code>len</code> is set
   * at 30.
   */
  DYNAMIC_PROGRAMMING() {

    private int len = 30;
    private int curr = 2;
    private int[] answer = new int[len];

    {
      answer[0] = 0;
      answer[1] = 1;
    }

    @Override
    public int solve(int n) {
      if (n < curr && answer[n] != 0 || n == 0) return answer[n];
      for (; curr - 1 < Math.min(len - 1, n); curr++) {
        answer[curr] = answer[curr - 1] + answer[curr - 2];
      }
      if (n < len) return answer[n];
      int pre2 = answer[curr - 2];
      int pre1 = answer[curr - 1];
      for (int i = curr; i < n; i++) {
        int temp = pre2 + pre1;
        pre2 = pre1;
        pre1 = temp;
      }
      return pre2;
    }

  };

  public abstract int solve(int n);

  public static class TestFibonacci {

    private int[] expected = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34};

    @Test
    public void testRecursiveSolution() {
      for (int i = 0; i < expected.length; i++) {
        Assert.assertEquals(expected[i], Fibonacci.RECUSIVE_SOLUTION.solve(i));
      }
    }

    @Test
    public void testDynamicProgramming() {
      for (int i = 0; i < expected.length; i++) {
        Assert.assertEquals(expected[i], Fibonacci.DYNAMIC_PROGRAMMING.solve(i));
      }
    }

  }

}

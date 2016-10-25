package org.mo39.fmbh.algorithm.dynamicprogramming;

import org.junit.Assert;
import org.junit.Test;

public enum Fibonacci {

  RECUSIVE_SOLUTION() {

    @Override
    public int solve(int n) {
      if (n == 0) return 0;
      if (n == 1) return 1;
      return solve(n - 2) + solve(n - 1);
    }

  },

  /**
   * //TODO store answer in static field and make the solution return immediately if it's already
   * calculated.
   */
  DYNAMIC_PROGRAMMING() {

    @Override
    public int solve(int n) {
      answer = new int[n];
      if (n == 1) return 0;
      answer[1] = 1;
      for (int i = 2; i < n; i++) {
        answer[i] = answer[i - 1] + answer[i - 2];
      }
      return answer[n - 1];
    }

  };

  public int[] answer;

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
      Fibonacci sol = Fibonacci.DYNAMIC_PROGRAMMING;
      sol.solve(expected.length);
      Assert.assertArrayEquals(expected, sol.answer);
    }

  }

}

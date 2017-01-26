package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given a positive integer n, find the least number of perfect square numbers
 * (for example, 1, 4, 9, 16, ...) which sum to n.
 * 
 * 
 * 
 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return
 * 2 because 13 = 4 + 9.
 * 
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/perfect-squares/">Perfect Squares</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum PerfectSquares {

  RECURSIVE_SOLUTION {

    @Override
    public int solve(int n) {
      List<Integer> list = new ArrayList<>();
      int i = 1, square = -1;
      while ((square = i * i) <= n) {
        if (square == n) return 1;
        list.add(square);
        i++;
      }
      return recur(list, n);
    }


    private int recur(List<Integer> list, int n) {
      if (n == 0) return 0;
      int min = Integer.MAX_VALUE;
      for (int i = 0; i < list.size(); i++) {
        if (n - list.get(i) < 0) break;
        min = Math.min(min, 1 + recur(list, n - list.get(i)));
      }
      return min;
    }

  },

  TOP_DOWN_WITH_MEMO {

    @Override
    public int solve(int n) {
      List<Integer> list = new ArrayList<>();
      int i = 1, square = -1;
      while ((square = i * i) <= n) {
        if (square == n) return 1;
        list.add(square);
        i++;
      }
      return recur(list, n, new int[n + 1]);
    }


    private int recur(List<Integer> list, int n, int[] memo) {
      if (n == 0) return 0;
      int min = Integer.MAX_VALUE;
      for (int i = 0; i < list.size(); i++) {
        if (n - list.get(i) < 0) break;
        if (memo[n - list.get(i)] == 0) memo[n - list.get(i)] = recur(list, n - list.get(i), memo);
        min = Math.min(min, 1 + memo[n - list.get(i)]);
      }
      return min;
    }

  },

  BOTTOM_UP_METHOD_0 {

    @Override
    public int solve(int n) {
      List<Integer> list = new ArrayList<>();
      int[] memo = new int[n + 1];
      int i = 1, square = -1;
      while ((square = i * i) <= n) {
        if (square == n) return 1;
        list.add(square);
        i++;
      }
      for (i = 0; i < list.size(); i++) {
        memo[list.get(i)] = 1;
        for (int j = 1; j + list.get(i) <= n; j++) {
          if (memo[j] != 0) {
            int newCount = memo[j] + 1, sum = j + list.get(i);
            if (memo[sum] != 0) memo[sum] = Math.min(memo[sum], newCount);
            else memo[sum] = newCount;
          }
        }
      }
      return memo[n];
    }

  },

  BOTTOM_UP_METHOD_1 {

    @Override
    public int solve(int n) {
      List<Integer> list = new ArrayList<>();
      int[] memo = new int[n + 1];
      int i = 1, square = -1;
      while ((square = i * i) <= n) {
        if (square == n) return 1;
        list.add(square);
        memo[list.get(i - 1)] = 1;
        for (int j = 1; j + list.get(i - 1) <= n; j++) {
          if (memo[j] != 0) {
            int newCount = memo[j] + 1, sum = j + list.get(i - 1);
            if (memo[sum] != 0) memo[sum] = Math.min(memo[sum], newCount);
            else memo[sum] = newCount;
          }
        }
        i++;
      }
      return memo[n];
    }

  },

  /**
   * The job of bottom up method is to build memo from bottom to up. So the outter loop should be
   * the memo.
   */
  BOTTOM_UP_METHOD_2 {

    @Override
    public int solve(int n) {
      int[] memo = new int[n + 1];
      Arrays.fill(memo, 1, memo.length, Integer.MAX_VALUE);
      for (int i = 1, square = i * i; (square = i * i) <= n; i++) {
        if (square == n) return 1;
        memo[square] = 1;
        for (int j = 1; j + square <= n; j++) {
          memo[j + square] = Math.min(memo[j + square], memo[j] + 1);
        }
      }
      return memo[n];
    }

  },

  BOTTOM_UP_METHOD_3 {

    @Override
    public int solve(int n) {
      int[] memo = new int[n + 1];
      Arrays.fill(memo, 1, memo.length, Integer.MAX_VALUE);
      for (int i = 0; i <= n; i++) {
        for (int j = 1; i + j * j <= n; j++) {
          memo[i + j * j] = Math.min(memo[i + j * j], memo[i] + 1);
        }
      }
      return memo[n];
    }

  };

  public abstract int solve(int n);

  public static class TestPerfectSquares {

    private int n = 13;
    private int expected = 2;

    @Test
    public void testSolutions() {
      for (PerfectSquares sol : PerfectSquares.values()) {
        Assert.assertEquals(expected, sol.solve(n));
      }
    }

  }

}

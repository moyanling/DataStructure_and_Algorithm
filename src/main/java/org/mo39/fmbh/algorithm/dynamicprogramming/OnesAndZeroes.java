package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * In the computer world, use restricted resource you have to generate maximum
 * benefit is what we always want to pursue.
 * For now, suppose you are a dominator of m 0s and n 1s respectively. On the
 * other hand, there is an array with strings consisting of only 0s and 1s.
 * 
 * 
 * Now your task is to find the maximum number of strings that you can form with
 * given m 0s and n 1s. Each 0 and 1 can be used at most once.
 * 
 * 
 * 
 * Note:
 * 
 * The given numbers of 0s and 1s will both not exceed 100
 * The size of given string array won't exceed 600.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
 * Output: 4
 * 
 * Explanation: This are totally 4 strings can be formed by the using of 5 0s
 * and 3 1s, which are “10,”0001”,”1”,”0”
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: Array = {"10", "0", "1"}, m = 1, n = 1
 * Output: 2
 * 
 * Explanation: You could form "10", but then you'd have nothing left. Better
 * form "0" and "1".
 * </pre>
 * 
 * At the first glance, I thought this is a greedy problem, like: first sort them according the
 * length of the String, count the usage of 1 and 0. Pick them greedy. But it not. The optimal pick
 * can not result in the optimal result in some counter-example.
 * 
 * @see <a href="https://leetcode.com/problems/ones-and-zeroes/">Ones And Zeroes</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum OnesAndZeroes {

  RECURSIVE_SOLUTION {

    @Override
    public int solve(String[] strs, int m, int n) {
      return recur(strs, m, n, 0);
    }

    int recur(String[] strs, int m, int n, int i) {
      if (i >= strs.length) return 0;
      int max = Integer.MIN_VALUE;
      int[] counts = count(strs[i]);
      if (m >= counts[0] && n >= counts[1]) {
        max = Math.max(max, 1 + recur(strs, m - counts[0], n - counts[1], i + 1));
      }
      max = Math.max(max, recur(strs, m, n, i + 1));
      return max;
    }

  },

  /**
   * //TODO go back and understand this later.
   */
  BOTTOM_UP_METHOD {

    @Override
    public int solve(String[] strs, int m, int n) {
      int[][] memo = new int[m + 1][n + 1];
      for (String str : strs) {
        int[] counts = count(str);
        for (int i = m; i >= counts[0]; i--) {
          for (int j = n; j >= counts[1]; j--) {
            memo[i][j] = Math.max(1 + memo[i - counts[0]][j - counts[1]], memo[i][j]);
          }
        }
      }
      return memo[m][n];
    }

  };

  protected int[] count(String str) {
    int count = 0;
    for (char c : str.toCharArray()) {
      if (c == '0') count++;
    }
    return new int[] {count, str.length() - count};
  }

  public abstract int solve(String[] strs, int m, int n);

  public static class TestOnesAndZeroes {

    String[] strs = {"0", "1101", "01", "00111", "1", "10010", "0", "0", "00", "1", "11", "0011"};
    int m = 63;
    int n = 36;
    int expected = 12;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, RECURSIVE_SOLUTION.solve(strs, m, n));
      Assert.assertEquals(expected, BOTTOM_UP_METHOD.solve(strs, m, n));
    }

  }

}

package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given a string s, find the longest palindromic subsequence's length in s. You
 * may assume that the maximum length of s is 1000.
 * 
 * 
 * Example 1:
 * Input: 
 * 
 * "bbbab"
 * 
 * Output: 
 * 
 * 4
 * 
 * One possible longest palindromic subsequence is "bbbb".
 * 
 * 
 * Example 2:
 * Input:
 * 
 * "cbbd"
 * 
 * Output:
 * 
 * 2
 * 
 * One possible longest palindromic subsequence is "bb".
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/longest-palindromic-subsequence/">Longest Palindromic
 *      Subsequence</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum LongestPalindromicSubsequence {

  RECURSIVE_SOLUTION {

    @Override
    public int solve(String s) {
      return recur(s, 0, s.length() - 1);
    }

    int recur(String s, int start, int end) {
      if (start > end) return 0;
      if (start == end) return 1;
      int max = 1;
      if (s.charAt(start) == s.charAt(end)) max = Math.max(max, 2 + recur(s, start + 1, end - 1));
      else {
        max = Math.max(max, recur(s, start + 1, end));
        max = Math.max(max, recur(s, start, end - 1));
      }
      return max;
    }

  },

  TOP_DOWN_WITH_MEMO {

    @Override
    public int solve(String s) {
      return recur(s, 0, s.length() - 1, new int[s.length()][s.length()]);
    }

    int recur(String s, int start, int end, int[][] memo) {
      if (memo[start][end] != 0) return memo[start][end];
      if (start > end) return 0;
      if (start == end) return 1;
      int max = 1;
      if (s.charAt(start) == s.charAt(end))
        max = Math.max(max, 2 + recur(s, start + 1, end - 1, memo));
      else {
        max = Math.max(max, recur(s, start + 1, end, memo));
        max = Math.max(max, recur(s, start, end - 1, memo));
      }
      memo[start][end] = max;
      return max;
    }

  },

  BOTTOM_UP_METHOD {

    @Override
    public int solve(String s) {
      int memo[][] = new int[s.length()][s.length()];
      for (int i = s.length() - 1; i >= 0; i--) {
        memo[i][i] = 1;
        for (int j = i + 1; j < s.length(); j++) {
          if (s.charAt(i) == s.charAt(j)) memo[i][j] = Math.max(memo[i][j], memo[i + 1][j - 1] + 2);
          else {
            memo[i][j] = Math.max(memo[i][j], memo[i + 1][j]);
            memo[i][j] = Math.max(memo[i][j], memo[i][j - 1]);
          }
        }
      }
      return memo[0][s.length() - 1];
    }

  };

  public abstract int solve(String s);

  public static class TestLongestPalindromicSubsequence {

    String s = "sdagrsds";
    int expected = 5;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, RECURSIVE_SOLUTION.solve(s));
      Assert.assertEquals(expected, TOP_DOWN_WITH_MEMO.solve(s));
      Assert.assertEquals(expected, BOTTOM_UP_METHOD.solve(s));
    }

  }

}

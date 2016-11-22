package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.INTRODUCTION_TO_ALGORITHM;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * Given two string, find the longest common sub-sequence.
 *
 * @author Jihan Chen
 *
 */
@ProblemSource(INTRODUCTION_TO_ALGORITHM)
public enum LongestCommonSubsequence {

  /**
   * After learning this solution, I got the feeling that for every recursive problem/solution, I
   * should provide a <b>Recurrence Formula</b> to show<br>
   * 1. how a problem can solve it's previous cases using the same solution and, <br>
   * 2. the stop case of the recurrence.<br>
   * <p>
   * It is clear that, given this formula/function, I can implement the code easily with a good
   * understanding of the problem. And, a implementation without such formula may allow me to
   * swallow some wrong understanding.
   * <p>
   * Recurrence Formula:<br>
   *
   * <pre>
   * Given X = &lt;x[1], x[2], ..., x[m]&gt;
   * for i = 0 , 1, ..., m,
   * Define X[i] = &lt;x[1], x[2], ..., x[i]&gt; is the i<sup>th</sup> prefix of X.
   * And define c[i,j] is the length of the common subsequence of X[i] and Y[j].
   *
   *          | 0                           if i=0 or j=0
   * c[i,j] = | c[i-1,j-1] + 1              if i,j>0 and x[i] = y[j]
   *          | max(c[i,j-1], c[i-1,j])     if i,j>0 and x[i] != y[j]
   * </pre>
   */
  RECURSIVE_SOLUTION() {

    @Override
    public char[] solve(String s1, String s2) {
      return recur(s1.toCharArray(), s1.length(), s2.toCharArray(), s2.length());
    }

    private char[] recur(char[] charArr1, int end1, char[] charArr2, int end2) {
      if (end1 == 0 || end2 == 0) return new char[0];
      if (charArr1[end1 - 1] == charArr2[end2 - 1])
        return ArrayUtils.add(recur(charArr1, end1 - 1, charArr2, end2 - 1), charArr1[end1 - 1]);
      char[] result1 = recur(charArr1, end1 - 1, charArr2, end2);
      char[] result2 = recur(charArr1, end1, charArr2, end2 - 1);
      return result1.length > result2.length ? result1 : result2;
    }

  },

  /**
   * This one is nothing more than add a memo to the recursive solution.
   *
   */
  TOP_DOWN_WITH_MEMO() {

    private char[][][] memo;

    @Override
    public char[] solve(String s1, String s2) {
      memo = new char[s1.length() + 1][s2.length() + 1][];
      return recur(s1.toCharArray(), s1.length(), s2.toCharArray(), s2.length());
    }

    private char[] recur(char[] charArr1, int end1, char[] charArr2, int end2) {
      if (end1 == 0 || end2 == 0) return new char[0];
      if (memo[end1][end2] != null) return memo[end1][end2];
      if (charArr1[end1 - 1] == charArr2[end2 - 1]) {
        memo[end1 - 1][end2 - 1] = recur(charArr1, end1 - 1, charArr2, end2 - 1);
        return ArrayUtils.add(memo[end1 - 1][end2 - 1], charArr1[end1 - 1]);
      }
      char[] result1 = recur(charArr1, end1 - 1, charArr2, end2);
      char[] result2 = recur(charArr1, end1, charArr2, end2 - 1);
      if (result1.length > result2.length) {
        memo[end1 - 1][end2] = result1;
        return result1;
      } else {
        memo[end1][end2 - 1] = result2;
        return result2;
      }
    }

  },

  /**
   * //TODO 15.4-4
   *
   */
  BOTTOM_UP_METHOD() {

    private char[][][] memo;

    @Override
    public char[] solve(String s1, String s2) {
      memo = new char[s1.length()][s2.length()][];
      char[] charArr1 = s1.toCharArray();
      char[] charArr2 = s2.toCharArray();
      for (int i = 1; i < s1.length() + 1; i++) {
        for (int j = 1; j < s2.length() + 1; j++) {
          if (charArr1[i - 1] == charArr2[j - 1])
            memo[i - 1][j - 1] = ArrayUtils.add(memo(i - 1, j - 1), charArr1[i - 1]);
          else if (memo(i - 1, j).length < memo(i, j - 1).length)
            memo[i - 1][j - 1] = memo(i, j - 1);
          else memo[i - 1][j - 1] = memo(i - 1, j);
        }
      }
      return memo[charArr1.length - 1][charArr2.length - 1];
    }

    private char[] memo(int i, int j) {
      if (i == 0 || j == 0) return new char[0];
      return memo[i - 1][j - 1];
    }

  };

  public abstract char[] solve(String s1, String s2);

  @SuppressWarnings("serial")
  public static class TestLongestCommonSubsequence {

    private String s1 = "ABCBDAB";
    private String s2 = "BDCABA";

    private List<char[]> list = new ArrayList<char[]>() {
      {
        add(new char[] {'B', 'D', 'A', 'B'});
        add(new char[] {'B', 'C', 'A', 'B'});
        add(new char[] {'B', 'C', 'B', 'A'});
      }
    };

    @Test
    public void testSolutions() {
      Assert.assertThat(list, hasItems(RECURSIVE_SOLUTION.solve(s1, s2)));
      Assert.assertThat(list, hasItems(TOP_DOWN_WITH_MEMO.solve(s1, s2)));
      Assert.assertThat(list, hasItems(BOTTOM_UP_METHOD.solve(s1, s2)));
    }
  }
}

package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.hamcrest.CoreMatchers.hasItems;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Given two string, find the longest common sub-sequence.
 *
 * @author Jihan Chen
 *
 */
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
   * And define c[i,j] is the length of the LCS of X[i] and Y[j].
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

  ITERATIVE_SOLUTION() {

    @Override
    public char[] solve(String s1, String s2) {
      // TODO Auto-generated method stub
      return null;
    }

  };

  public abstract char[] solve(String s1, String s2);

  @SuppressWarnings("serial")
  public static class TestLongestCommonSubsequence {

    private List<char[]> list = new ArrayList<char[]>() {
      {
        add(new char[] {'B', 'D', 'A', 'B'});
        add(new char[] {'B', 'C', 'A', 'B'});
      }
    };

    @Test
    public void testRecursiveSolution() {
      Assert.assertThat(list,
          hasItems(LongestCommonSubsequence.RECURSIVE_SOLUTION.solve("ABCBDAB", "BDCABA")));
    }

  }

}

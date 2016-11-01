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

  };

  public abstract char[] solve(String s1, String s2);

  @SuppressWarnings("serial")
  public static class TestLongestCommonSubsequence {

    private List<char[]> expected = new ArrayList<char[]>() {
      {
        add(new char[] {'B', 'D', 'A', 'B'});
        add(new char[] {'B', 'C', 'A', 'B'});
      }
    };

    @Test
    public void testRecursiveSolution() {
      Assert.assertThat(expected,
          hasItems(LongestCommonSubsequence.RECURSIVE_SOLUTION.solve("ABCBDAB", "BDCABA")));
    }

  }

}

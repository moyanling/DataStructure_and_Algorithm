package org.mo39.fmbh.datastructure.string;

import static org.mo39.fmbh.common.Z.reverse;
import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a string, you need to reverse the order of characters in each word within
 * a sentence while still preserving whitespace and initial word order.
 * 
 * Example 1:
 * 
 * Input: "Let's take LeetCode contest"
 * Output: "s'teL ekat edoCteeL tsetnoc"
 * 
 * 
 * 
 * Note:
 * In the string, each word is separated by single space and there will not be
 * any extra space in the string.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/reverse-words-in-a-string-iii/">Reverse Words In A
 *      String III</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum ReverseWordsInAStringIII {

  SOLUTION {

    @Override
    public String solve(String s) {
      if (s == null || s.length() <= 1) return s;
      char[] arr = s.toCharArray();
      int pre = 0;
      for (int i = 0; i < s.length(); i++) {
        if (arr[i] == ' ') {
          reverse(arr, pre, i);
          pre = i + 1;
        }
      }
      reverse(arr, pre, s.length());
      return String.valueOf(arr);
    }

  };

  public abstract String solve(String s);

  public static class TestReverseWordsInAStringIII {

    String s = "Let's take LeetCode contest";
    String expected = "s'teL ekat edoCteeL tsetnoc";

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(s));
    }

  }

}

package org.mo39.fmbh.datastructure.string;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a non-empty string check if it can be constructed by taking a substring of it and appending
 * multiple copies of the substring together. You may assume the given string consists of lowercase
 * English letters only and its length will not exceed 10000.
 * 
 * Example 1:
 * 
 * Input: "abab"
 * 
 * Output: True
 * 
 * Explanation: It's the substring "ab" twice.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: "aba"
 * 
 * Output: False
 * 
 * 
 * 
 * Example 3:
 * 
 * Input: "abcabcabcabc"
 * 
 * Output: True
 * 
 * Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/repeated-substring-pattern/">Repeated Substring
 *      Pattern</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum RepeatedSubstringPattern {

  BRUTE_FORCE {

    @Override
    public boolean solve(String str) {
      if (str == null || str.length() == 1) return false;
      int repeatedTimes = 1;
      while (++repeatedTimes <= str.length()) {
        if (str.length() % repeatedTimes != 0) continue;
        int subLen = str.length() / repeatedTimes;
        for (int i = 0; i < subLen; i++) {
          boolean allTheSame = true;
          for (int j = 1; j < repeatedTimes; j++) {
            if (str.charAt(i) != str.charAt(i + j * subLen)) {
              allTheSame = false;
              break;
            }
          }
          if (!allTheSame) break;
          if (allTheSame && i == subLen - 1) return true;
        }
      }
      return false;
    }

  },

  /**
   * Backreference using "\\1" and repeat more than one time using "+".
   */
  REGULAR_EXPRESSION {

    @Override
    public boolean solve(String str) {
      return str.matches("^(.+)\\1+$");
    }

  },

  KMP {

    @Override
    public boolean solve(String str) {
      // TODO Auto-generated method stub
      return false;
    }

  };

  public abstract boolean solve(String str);

  public static class TestRepeatedSubstringPattern {

    private String expectingTrue = "abcabcabcabc";
    private String expectingFalse = "aba";

    @Test
    public void testSolutions() {
      Assert.assertTrue(BRUTE_FORCE.solve(expectingTrue));
      Assert.assertFalse(BRUTE_FORCE.solve(expectingFalse));

      Assert.assertTrue(REGULAR_EXPRESSION.solve(expectingTrue));
      Assert.assertFalse(REGULAR_EXPRESSION.solve(expectingFalse));
    }

  }

}
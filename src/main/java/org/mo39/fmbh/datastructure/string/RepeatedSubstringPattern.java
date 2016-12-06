package org.mo39.fmbh.datastructure.string;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

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

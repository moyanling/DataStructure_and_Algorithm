package org.mo39.fmbh.datastructure.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;


/**
 * searches for occurrences of a "word" W within a main "text string" S.
 *
 * @author Jihan Chen
 *
 */
public enum StringSearchingAlgorithm {

  BRUTE_FORCE_0 {

    @Override
    public int solve(String w, String s) {
      for (int i = 0; i < s.length(); i++) {
        boolean isMatch = true;
        for (int j = 0; j < w.length(); j++) {
          if (s.charAt(i + j) != w.charAt(j)) {
            isMatch = false;
            break;
          }
        }
        if (isMatch) return i;
      }
      return -1;
    }

  },

  BRUTE_FORCE_1 {

    @Override
    public int solve(String w, String s) {
      int i = 0, j = 0;
      while (i++ < s.length() && j < w.length()) {
        if (s.charAt(i) == w.charAt(j)) j++;
        else j = 0;
      }
      return j == w.length() ? i - j : -1;
    }

  },

  REGULAR_EXPRESSION {

    @Override
    public int solve(String w, String s) {
      Matcher m = Pattern.compile(w).matcher(s);
      if (m.find()) return m.start();
      return -1;
    }

  },

  /**
   * //TODO
   */
  KMP {

    @Override
    public int solve(String w, String s) {
      int i = 0;
      int j = 0;
      int[] next = new int[w.length()];
      while (i < s.length() && j < w.length()) {
        if (s.charAt(i) == w.charAt(j)) {
          i++;
          j++;
        } else if (j == 0) i++;
        else j = next[j - 1] + 1;
      }
      return j == w.length() ? i - j : -1;
    }

  };

  public abstract int solve(String w, String s);

  public static class TestStringSearchingAlgorithm {

    private String w = "world";
    private String s = "Hello, world.";
    private int expected = 7;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, s.indexOf(w));
      Assert.assertEquals(expected, BRUTE_FORCE_0.solve(w, s));
      Assert.assertEquals(expected, BRUTE_FORCE_1.solve(w, s));
      Assert.assertEquals(expected, REGULAR_EXPRESSION.solve(w, s));
      Assert.assertEquals(expected, KMP.solve(w, s));
    }

  }

}

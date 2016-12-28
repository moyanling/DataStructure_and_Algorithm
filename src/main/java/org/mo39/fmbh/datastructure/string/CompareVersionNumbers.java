package org.mo39.fmbh.datastructure.string;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Compare two version numbers version1 and version2.
 * If version1 &gt; version2 return 1, if version1 &lt; version2 return -1, otherwise
 * return 0.
 * 
 * You may assume that the version strings are non-empty and contain only digits
 * and the . character.
 * The . character does not represent a decimal point and is used to separate
 * number sequences.
 * For instance, 2.5 is not "two and a half" or "half way to version three", it
 * is the fifth second-level revision of the second first-level revision.
 * 
 * Here is an example of version numbers ordering:
 * 0.1 &lt; 1.1 &lt; 1.2 &lt; 13.37
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/compare-version-numbers/">Compare Version Numbers</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum CompareVersionNumbers {

  SOLUTION_0 {

    @Override
    public int solve(String version1, String version2) {
      String[] v1 = version1.split("\\.");
      String[] v2 = version2.split("\\.");
      int i = 0, len1 = v1.length + 1, len2 = v2.length + 1;
      while (--len1 > 1 && Integer.valueOf(v1[len1 - 1]) == 0) {
      }
      while (--len2 > 1 && Integer.valueOf(v2[len2 - 1]) == 0) {
      }
      for (; i < len1 && i < len2; i++) {
        if (Integer.valueOf(v1[i]) < Integer.valueOf(v2[i])) return -1;
        if (Integer.valueOf(v1[i]) > Integer.valueOf(v2[i])) return 1;
      }
      if (i == len1 && i == len2) return 0;
      return i == len1 ? -1 : 1;
    }

  },

  SOLUTION_1 {

    @Override
    public int solve(String version1, String version2) {
      String[] v1 = version1.split("\\.");
      String[] v2 = version2.split("\\.");
      int i = 0;
      for (; i < v1.length || i < v2.length; i++) {
        Integer i1 = i >= v1.length ? 0 : Integer.valueOf(v1[i]);
        Integer i2 = i >= v2.length ? 0 : Integer.valueOf(v2[i]);
        if (i1 < i2) return -1;
        if (i1 > i2) return 1;
      }
      return 0;
    }

  };

  public abstract int solve(String version1, String version2);

  public static class TestCompareVersionNumbers {

    private String version1 = "1.0";
    private String version2 = "1";
    private int expected = 0;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION_0.solve(version1, version2));
      Assert.assertEquals(expected, SOLUTION_1.solve(version1, version2));
    }
  }

}

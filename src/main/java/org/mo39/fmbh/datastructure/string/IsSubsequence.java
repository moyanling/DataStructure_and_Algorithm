package org.mo39.fmbh.datastructure.string;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 *
 * Given a string s and a string t, check if s is subsequence of t.
 *
 *
 *
 * You may assume that there is only lower case English letters in both s and
 * t. t is potentially a very long (length ~= 500,000) string, and s is a short
 * string (<=100).
 *
 *
 *
 * A subsequence of a string is a new string which is formed from the original
 * string by deleting some (can be none) of the characters without disturbing
 * the relative positions of the remaining characters. (ie, "ace" is a subsequence
 * of "abcde" while "aec" is not).
 *
 *
 * Example 1:
 * s = "abc", t = "ahbgdc"
 *
 *
 * Return true.
 *
 *
 * Example 2:
 * s = "axc", t = "ahbgdc"
 *
 *
 * Return false.
 *
 *
 * Follow up:
 * If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you
 * want to check one by one to see if T has its subsequence. In this scenario,
 * how would you change your code?
 *
 * </pre>
 *
 * @see <a href="https://leetcode.com/problems/is-subsequence/">Is Subsequence</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum IsSubsequence {

  SOLUTION_0 {

    @Override
    public boolean solve(String s, String t) {
      if (s.length() < 1) return true;
      char[] arr = t.toCharArray();
      int j = 0;
      for (int i = 0; i < t.length(); i++) {
        if (arr[i] == s.charAt(j)) {
          j++;
          if (j == s.length()) return true;
        }
      }
      return false;
    }

  },

  SOLUTION_1 {

    @Override
    public boolean solve(String s, String t) {
      if (s.length() == 0) return true;
      int pre = t.indexOf(s.charAt(0));
      if (pre == -1) return false;
      for (int i = 1; i < s.length(); i++) {
        int posi;
        if ((posi = t.indexOf(s.charAt(i), pre + 1)) == -1) return false;
        else pre = posi;
      }
      return true;
    }

  };

  public abstract boolean solve(String s, String t);

}

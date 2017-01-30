package org.mo39.fmbh.algorithm.slidingwindow;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a string, find the length of the longest substring without repeating
 * characters.
 * 
 * Examples:
 * 
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * 
 * Given "bbbbb", the answer is "b", with the length of 1.
 * 
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer
 * must be a substring, "pwke" is a subsequence and not a substring.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/longest-substring-without-repeating-characters/">
 *      Longest Substring Without Repeating Characters</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum LongestSubstringWithoutRepeatingCharacters {

  SOLUTION_0 {

    @Override
    public int solve(String s) {
      if (s == null || s.equals("")) return 0;
      int start = 0, end = 0, len = 1;
      Set<Character> set = new HashSet<>();
      for (; end < s.length(); end++) {
        char c = s.charAt(end);
        if (set.contains(c)) {
          for (; s.charAt(start) != c; start++) {
            set.remove(s.charAt(start));
          }
          start++;
        } else {
          set.add(c);
          len = Math.max(len, end - start + 1);
        }
      }
      return len;
    }

  },

  SOLUTION_1 {

    @Override
    public int solve(String s) {
      if (s.length() == 0) return 0;
      Map<Character, Integer> map = new HashMap<>();
      int max = 0;
      for (int i = 0, j = 0; i < s.length(); ++i) {
        if (map.containsKey(s.charAt(i))) {
          j = Math.max(j, map.get(s.charAt(i)) + 1);
        }
        map.put(s.charAt(i), i);
        max = Math.max(max, i - j + 1);
      }
      return max;
    }

  };

  public abstract int solve(String s);

  public static class TestLongestSubstringWithoutRepeatingCharacters {

    String s = "pwwkew";
    int expected = 3;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION_0.solve(s));
    }

  }

}
